package mapwriter.region;

public interface IChunk {
    int getBlockAndMetadata(int x, int y, int z);

    int getBiome(int x, int z);

    int getLightValue(int x, int y, int z);

    int getMaxY();
}
