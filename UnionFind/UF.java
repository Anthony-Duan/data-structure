package UnionFind;

/**
 * @ Description: 并查集接口
 * @ Date: Created in 13:16 22/07/2018
 * @ Author: Anthony_Duan
 */
public interface UF {
    int getSize();

    boolean isConnected(int p, int q);

    void unionElements(int p, int q);
}
