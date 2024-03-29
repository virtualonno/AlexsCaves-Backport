package com.github.alexmodguy.alexscaves.mixin.client;

/*@Mixin(SpriteResourceLoader.class)
public abstract class SpriteResourceLoaderMixin {

    @Inject(method = "load",
            at = @At("RETURN"))
    private static void ac_load(ResourceManager resourceManager, ResourceLocation location, CallbackInfoReturnable<SpriteResourceLoader> cir) {
        if (location.getPath().equals("armor_trims")) {
            SpriteResourceLoader ret = cir.getReturnValue();
            for (SpriteSource source : ((SpriteResourceLoaderMixin) (Object) ret).getSources()) {
                if (source instanceof PalettedPermutationsAccessor permutations && permutations.getPaletteKey().getPath().equals("trims/color_palettes/trim_palette")) {
                    ResourceLocation trimLocation = new ResourceLocation(AlexsCaves.MODID, "trims/models/armor/polarity");
                    ResourceLocation leggingsTrimLocation = new ResourceLocation(AlexsCaves.MODID, "trims/models/armor/polarity").withSuffix("_leggings");
                    permutations.setTextures(ImmutableList.<ResourceLocation>builder().addAll(permutations.getTextures()).add(trimLocation, leggingsTrimLocation).build());
                }
            }
        }
    }

    @Accessor("sources")
    abstract List<SpriteSource> getSources();

    @Mixin(PalettedPermutations.class)
    private interface PalettedPermutationsAccessor {

        @Accessor
        List<ResourceLocation> getTextures();

        @Accessor("textures")
        @Mutable
        void setTextures(List<ResourceLocation> value);

        @Accessor
        ResourceLocation getPaletteKey();
    }
}*/
