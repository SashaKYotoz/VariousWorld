package net.sashakyotoz.variousworld.mixin;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.MultiPackResourceManager;
import net.minecraft.server.packs.resources.Resource;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.common.config.ConfiguredData;
import net.sashakyotoz.variousworld.common.config.ConfiguredDataResourcePack;
import net.sashakyotoz.variousworld.common.config.IResourceExistence;
import org.apache.commons.io.input.CharSequenceInputStream;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

//following code takes its place from EP of genius of Lyof:
//https://github.com/Lyof429/EndsPhantasm/blob/master/src/main/java/net/lyof/phantasm/mixin/LifecycledResourceManagerImplMixin.java

@Mixin(MultiPackResourceManager.class)
public class MultiPackResourceManagerMixin implements IResourceExistence {
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(PackType type, List packs, CallbackInfo ci) {
        ConfiguredData.MANAGER_KEEPER.put(0, (MultiPackResourceManager) ((Object) this));
    }

    @Unique
    private static Resource readAndApply(Optional<Resource> resource, ConfiguredData data) {
        VariousWorld.LOGGER.info("Applying configured data: {}", data.target);

        String result = "";
        if (resource.isEmpty())
            result = data.apply(null);
        else {
            try {
                result = data.apply(new String(resource.get().open().readAllBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String finalResult = result;
        return new Resource(ConfiguredDataResourcePack.INSTANCE,
                () -> new CharSequenceInputStream(finalResult, Charset.defaultCharset()));
    }

    @Unique
    private static Resource readAndApply(Resource resource, ConfiguredData data) {
        if (resource.source() instanceof ConfiguredDataResourcePack) return resource;
        return readAndApply(Optional.of(resource), data);
    }

    @Inject(method = "getResource", at = @At("RETURN"), cancellable = true)
    public void getConfiguredResource(ResourceLocation id, CallbackInfoReturnable<Optional<Resource>> cir) {
        ConfiguredData data = ConfiguredData.get(id);
        if (data == null || !data.enabled.get() || (cir.getReturnValue().isPresent() && cir.getReturnValue().get().source() instanceof ConfiguredDataResourcePack))
            return;

        cir.setReturnValue(Optional.of(readAndApply(cir.getReturnValue(), data)));
    }

    @Inject(method = "getResourceStack", at = @At("RETURN"), cancellable = true)
    public void getAllConfiguredResource(ResourceLocation id, CallbackInfoReturnable<List<Resource>> cir) {
        ConfiguredData data = ConfiguredData.get(id);
        if (data == null || !data.enabled.get()) return;

        List<Resource> result = cir.getReturnValue().stream()
                .map(resource -> readAndApply(resource, data)).toList();
        cir.setReturnValue(result);
    }

    @Inject(method = "listResources", at = @At("RETURN"), cancellable = true)
    public void findConfiguredResources(String startingPath, Predicate<ResourceLocation> allowedPathPredicate,
                                        CallbackInfoReturnable<Map<ResourceLocation, Resource>> cir) {

        for (ConfiguredData data : ConfiguredData.INSTANCES) {
            if (data.enabled.get() && data.target.getPath().startsWith(startingPath + "/") && allowedPathPredicate.test(data.target)) {
                if (!cir.getReturnValue().containsKey(data.target)) {
                    cir.getReturnValue().put(data.target, readAndApply(Optional.empty(), data));
                }
            }
        }

        List<ResourceLocation> ids = cir.getReturnValue().keySet().stream().toList();
        for (ResourceLocation id : ids) {
            ConfiguredData data = ConfiguredData.get(id);
            if (data == null || !data.enabled.get()) continue;

            cir.getReturnValue().replace(id, readAndApply(cir.getReturnValue().get(id), data));
        }
    }

    @Inject(method = "listResourceStacks", at = @At("RETURN"), cancellable = true)
    public void findAllConfiguredResources(String startingPath, Predicate<ResourceLocation> allowedPathPredicate,
                                           CallbackInfoReturnable<Map<ResourceLocation, List<Resource>>> cir) {

        for (ConfiguredData data : ConfiguredData.INSTANCES) {
            if (data.enabled.get() && data.target.getPath().startsWith(startingPath) && allowedPathPredicate.test(data.target)) {
                if (!cir.getReturnValue().containsKey(data.target)) {
                    cir.getReturnValue().put(data.target, List.of(readAndApply(Optional.empty(), data)));
                }
            }
        }

        List<ResourceLocation> ids = cir.getReturnValue().keySet().stream().toList();
        for (ResourceLocation id : ids) {
            ConfiguredData data = ConfiguredData.get(id);
            if (data == null || !data.enabled.get()) continue;

            cir.getReturnValue().replace(id, cir.getReturnValue().get(id).stream()
                    .map(resource -> readAndApply(resource, data)).toList());
        }
    }

    @Override
    public boolean resourceExists(ResourceLocation id) {
        return ((MultiPackResourceManager)(Object)this).getResource(id).isPresent();
    }
}