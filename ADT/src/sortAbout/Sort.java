package sortAbout;

import java.util.jar.JarEntry;

public final class Sort {
    //final类不能被继承，不能被覆盖，以及final类在执行速度方面比一般类快。
    // 由于它的方法不能够被覆盖，所以其地址引用和装载在编译期间完成，而不是在运行期间由JVM进行复杂的装载，因而简单和有效。所以如果没有必要，或者不存在有继承的可能性时，尽量使用final类。当然，在API类库中不多使用final类是因为它们是标准程序，希望在实际软件开发中得以广泛使用。
    //注意final数据和final类的不同。final数据指常量，即其值一旦初始化，就不能改变。而final类则指不能被其他类所继承的类。

    /**
     * 插入排序-1趟排序组成，基本思想是，第i趟排序，就把第i个元素与前面的元素进行比较，放在合适的位置，
     * 注意这里使用了一个非三次赋值的换序方法，空穴
     * @param a : the array to sort
     * */
    public static <AnyType extends Comparable<? super AnyType>>
    void insertingSort(AnyType [] a){
        AnyType tmp;
        int j;
        for (int i = 0 ; i < a.length ; i++){
            tmp = a[i];
            for (j = i ; j >0 && tmp.compareTo(a[j-1])<0 ; j--)
                a[j] = a[j-1];
            a[j] = tmp;
        }
    }
    /**
     * 希尔排序，一种典型的多趟排序算法，冲破了二次时间障，因为不再是相邻元素之间进行排序了
     * 该方法使用的是流行的增量序列：Length/2 ， length/2/2
     * */
    public static <AnyType extends Comparable<? super AnyType>>
    void shellSort(AnyType [] a)
    {
        int j;
        for (int gap = a.length/2 ; gap > 0 ; gap /=2 )
            for (int i = gap ; i<a.length ; i++){
                AnyType tmp = a[i];
                for (j = i ; j>=gap && tmp.compareTo(a[j-gap]) < 0; j-=gap)
                    a[j] = a[j-gap];
                a[j] = tmp;
            }
    }



}