package com.isoran.bearmode.particles;

import com.isoran.bearmode.util.Registration;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.fml.RegistryObject;


public class ModParticles {

    public static final RegistryObject<BasicParticleType> TIMISOREANA_PARTICLE
            = Registration.PARTICLE_TYPES.register("timisoreana_particle", () -> new BasicParticleType(false));


    public static void register() {}

}