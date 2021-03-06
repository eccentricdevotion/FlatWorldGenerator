package me.eccentric_nz.flatworldgenerator;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;

import java.util.Random;

public class FlatGenerator extends ChunkGenerator {

    private final FlatWorldGenerator plugin;
    private final Material bottom;
    private final Material rock;
    private final Material middle;
    private final Material surface;

    public FlatGenerator(FlatWorldGenerator plugin) {
        this.plugin = plugin;
        this.bottom = Material.valueOf(plugin.getConfig().getString("bottom"));
        this.rock = Material.valueOf(plugin.getConfig().getString("rock"));
        this.middle = Material.valueOf(plugin.getConfig().getString("middle"));
        this.surface = Material.valueOf(plugin.getConfig().getString("surface"));
    }

    @Override
    public void generateSurface(WorldInfo worldInfo, Random random, int x, int z, ChunkGenerator.ChunkData chunkData) {
        if (chunkData.getMinHeight() == worldInfo.getMinHeight()) {
            for (int bx = 0; bx < 16; bx++) {
                for (int bz = 0; bz < 16; bz++) {
                    for (int by = 1; by < 65; by++) {
                        if (by < 60) {
                            chunkData.setBlock(bx, chunkData.getMinHeight() + by, bz, rock);
                        } else if (by < 64) {
                            chunkData.setBlock(bx, chunkData.getMinHeight() + by, bz, middle);
                        } else {
                            chunkData.setBlock(bx, chunkData.getMinHeight() + by, bz, surface);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void generateBedrock(WorldInfo worldInfo, Random random, int x, int z, ChunkGenerator.ChunkData chunkData) {
        if (chunkData.getMinHeight() == worldInfo.getMinHeight()) {
            for (int bx = 0; bx < 16; bx++) {
                for (int bz = 0; bz < 16; bz++) {
                    chunkData.setBlock(bx, chunkData.getMinHeight(), bz, bottom);
                }
            }
        }
    }

    @Override
    public BiomeProvider getDefaultBiomeProvider(WorldInfo worldInfo) {
        return new FlatBiomeProvider();
    }

    @Override
    public Location getFixedSpawnLocation(World world, Random random) {
        return new Location(world, 0, -60, 0);
    }

    @Override
    public boolean shouldGenerateNoise() {
        return false;
    }

    @Override
    public boolean shouldGenerateSurface() {
        return true;
    }

    @Override
    public boolean shouldGenerateBedrock() {
        return true;
    }

    @Override
    public boolean shouldGenerateCaves() {
        return false;
    }

    @Override
    public boolean shouldGenerateDecorations() {
        return false;
    }

    @Override
    public boolean shouldGenerateMobs() {
        return false;
    }

    @Override
    public boolean shouldGenerateStructures() {
        return false;
    }
}
