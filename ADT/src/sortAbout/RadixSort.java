package sortAbout;


import java.util.ArrayList;


public class RadixSort {
    /**
     * 一个简单的字符串基数排序，从一个字符串数组中，把每个字符串，按照最后一个字母排序、再按照倒数第二个字母排序……一直到第一个字母
     * */
    public static void radixSort1(String [] arr, int stringLen){
        //字符串应该是ASCII码，位于unicode的前256位
        final int BUCKETS = 256;
        //字符串数组的声明，注意，后面不能用<>，暂不知道原因，每个字符串由一个列表存储，且对于列表的列表是没有clear的，只有列表有
        ArrayList<String> [] buckets = new ArrayList [BUCKETS];

        //初始化每个存储字符串的列表
        for (int i = 0 ; i < BUCKETS ; i++)
            buckets[i] = new ArrayList<>();

        //从最后一个字母开始
        for (int pos = stringLen-1; pos>=0 ; pos--){

            //利用筒排序，从字符串数组arr中依次取出每一个字符串，依照该字符串的某个位置的ASCII码放入存储的数组buckets中
            for (String s : arr)
                buckets[s.charAt(pos)].add(s);

            int indx = 0;
            //便利buckets中的每个字符串列表，把列表转换为字符串，重新返回arr
            for (ArrayList<String> thisBucket : buckets){
                for (String s : thisBucket)
                    arr[indx++] = s;
                //清除每个元素也就清除了buckets
                thisBucket.clear();
            }

        }

    }

    /**
     * 基于计数器的基数排序思想，使用一个数组count来记录每个桶中，所以count[k]就是桶k中的元素
     * 然后我们用另一个数组offset，使得offset[k]表示值严格小于k的元素的个数
     * 定长的
     * */
    public static void countingRadixSort( String[] arr , int stringLen){
        final int BUCKETS = 256;

        int N = arr.length;
        String [] buffer = new String[N];
        //元素存储在in里
        String [] in = arr;
        //单趟排序结果存储在out里
        String [] out = buffer;

        for (int pos = stringLen -1; pos>=0 ; pos--){
            int [] count = new int[BUCKETS+1];

            //string数组中的某个位置的string，依照某一位的ASCII，使得count对应位置+1，用以记录桶的数目
            for (int i = 0; i< N; i++)

                count[ in[i].charAt(pos) +1 ]++;

            //原本把对应ASCII为k的桶的数目存入count的k+1处，将count[k]+count[k-1]可以得到ASCII严格小于k+1的元素的数目，注意这里是累计和，严格小于256的是整个数组长度
            // 存入count[k]，起到了和offset的效果
            for (int b = 1; b<=BUCKETS; b++)
                count[b] += count[ b-1 ];
            //严格小于第一个string的某个位置的ASCII（假设102）有4个，那么count[102] = 4, 那么out[4]放入第一个string，此时count[102] = 5, 下一次遇到严格小于102的元素会被排在out[5]。
            // 第二个string的对应位置的ASCII(假设98)有两个，那么count[98] = 2, 那么out[2]放入第二个原数组元素，count[98] = 3，
            for (int i = 0; i<N; i++)
                out[count[in[i].charAt(pos)]++] = in[i];

            String [] tmp = in;
            in = out;
            out = tmp;

        }

        //如果传递次数是奇数，则in是buffer，out是arr；所以复制回来
        if (stringLen%2 ==1 )
            for (int i = 0; i<arr.length; i++)
                out[i] = in[i];
    }

    //数组可以任意长，但需要告知最长string长度
    //首先把数组中的string按长度进行排序，由于字符串长度是一个小的整数，因此直接应用桶排序
    public static void radixSort( String [ ] arr, int maxLen )
    {
        final int BUCKETS = 256;

        ArrayList<String> [ ] wordsByLength = new ArrayList[ maxLen + 1 ];
        ArrayList<String> [ ] buckets = new ArrayList[ BUCKETS ];

        //注意这里第i个里面的也是一个列表，有其大小，比如wordsByLength[1]里存储了五个大小为1的string
        for( int i = 0; i < wordsByLength.length; i++ )
            wordsByLength[ i ] = new ArrayList<>( );

        for( int i = 0; i < BUCKETS; i++ )
            buckets[ i ] = new ArrayList<>( );

        //把单词长度放入桶里
        for( String s : arr )
            wordsByLength[ s.length( ) ].add( s );
        //单词长度桶排序
        int idx = 0;
        for( ArrayList<String> wordList : wordsByLength )
            for( String s : wordList )
                arr[ idx++ ] = s;


        //注意这里的pos是从数组中，string的最大长度开始减小
        int startingIndex = arr.length;
        for( int pos = maxLen - 1; pos >= 0; pos-- )
        {
            //把存储string长度的桶的，位于这个字符串字符位置的string的大小-=赋值给startingIndex，把长度最长的string的数量从string总数中减去
            //然后是长度次长的，etc，这种使得刚好长出来的那一个字符被排序
            startingIndex -= wordsByLength[ pos + 1 ].size( );
            //把长度最长的string桶排序，然后是长度次长的，etc，这种使得刚好长出来的那一个字符被排序
            for( int i = startingIndex; i < arr.length; i++ )
                buckets[ arr[ i ].charAt( pos ) ].add( arr[ i ] );

            idx = startingIndex;
            for( ArrayList<String> thisBucket : buckets )
            {
                for( String s : thisBucket )
                    arr[ idx++ ] = s;

                thisBucket.clear( );
            }
        }
    }

    public static void main(String[] args) {
        String s = "sisi";
        System.out.println((int) s.charAt(1));
    }
}
