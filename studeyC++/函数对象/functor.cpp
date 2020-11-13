//函数对象（函数符）使得可以像使用函数一样的利用（）使用对象
#include <iostream>
#include <list>
#include <iterator> 
#include <algorithm>

/**
 * 函数符：
 *  生成器：不用参数就可以调用的函数符
 * 一元函数：用一个参数就能调用的函数符     返回bool是谓词
 * 二元函数：用两个参数就能调用的函数符    返回bool是二元谓词
*/

//如果是已有一个接受两个参数的模板函数 bool TooBig(const T & val , const T & lim)可以向下面一样定义调用这个模板函数

template<class T>
class TooBig
{
private:
    T cutOff;
public:
    TooBig(const T & t) : cutOff(t){}
    bool operator()(const T & v ) {return v > cutOff;}  //定义()运算符允许我们像使用函数一样的使用对象，这个就叫做函数对象
    //bool operator()(const T & v) { return TooBig<T>(v , cutOff);}
    ~TooBig() {std::cout << "Bye ~~~" <<'\n';}
};


void outint(int n) { std::cout << n << " ";}
int main(int argc, const char** argv) {
    using std::list;
    using std::cout;
    using std::endl;

    TooBig<int> f100(100);
    int vals[10] = {50,100,90,263,84,97,54,200,9874,21};
    list<int> yadayada(vals , vals + 10);
    list<int> etcetera(vals , vals+10);
    //C++十一还可以这么干：list<int> yadayada =  {50,100,90,263,84,97,54,21,9874,21}
    cout<<"初始列表："<<'\n';
    for_each(yadayada.begin() , yadayada.end() , outint);
    cout<<endl;
    for_each(etcetera.begin() , etcetera.end() , outint);
    cout<<endl;

    yadayada.remove_if(f100);//调用的是一个对象
    //该函数接受的是一个谓词，如果谓词返回true，则删除这些对象，因此，大于100的全没了
    etcetera.remove_if(TooBig<int> (200)); //调用的是一个匿名对象，使用构造函数创建的
    cout<<"使用了谓词之后：\n";
    for_each(yadayada.begin() , yadayada.end() , outint);
    cout<<endl;
    for_each(etcetera.begin() , etcetera.end() , outint);
    cout<<endl;

    return 0;
}
