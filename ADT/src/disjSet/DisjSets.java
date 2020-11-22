package disjSet;

public class DisjSets {
    /**
     * 关键的点是，不要求find操作返回任何特定的名字，而是要求当且仅当两个元素属于相同集合时作用在两个元素上的find返回相同的元素
     * */
    public DisjSets (int numElements){
        s = new int[numElements];
        for (int i = 0; i<numElements; i++)
            s[i] = -1;
    }

    public void union(int root1, int root2 ){
        //s[root2] = root1; //简单的直接把父链连过去不是最好的办法

        //按高度求并
        if (s[root2] < s[root1])
            s[root1] = root2;
        else
        {
            if (s[root1] == s[root2])
                s[root1] --;
            s[root2] = root1;
        }
    }

    public int find(int x){
        if (s[x] < 0)
            return x;
        else
            return find(s[x]);
    }

    public int find1 (int x){
        //路径压缩
        if (s[x] < 0)
            return x;
        else
            return s[x]=find1(s[x]);
    }

    //储存每一个树的root节点，下标代表与不相交集类的元素，初始为-1，代表每一个元素的父链，在得到相等关系后，把其父链指向某一个节点的root
    private int [] s;
}
