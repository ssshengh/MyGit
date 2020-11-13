//使用函数适配器和自适应函数
#include <iostream>
#include <vector>
#include <iterator>
#include <algorithm>
#include <functional>  //包含了运算符所对应的函数符

void show (double);
const int LIM = 6;
int main(int argc, char const *argv[])
{
    using namespace std;
    double arr1[LIM] = {28,29,56,45,213,12};
    double arr2[LIM] = {64,2365,9,65,78,12};

    vector<double> gr8(arr1 , arr1 + LIM);
    vector<double> m8(arr2 , arr2 + LIM);
    cout.setf(ios_base::fixed);
    cout.precision(1);
    cout<<"gr8 : \t";
    for_each(gr8.begin() , gr8.end() , show);//最后个参数就是函数符，可以看出，这个函数符接收一个参数，一个一个的打印出来
    cout<<endl;
    cout<<"m8 : \t";
    for_each(m8.begin() , m8.end() , show);
    cout<<endl;

    vector<double> Sum(LIM);
    //函数符由于是自适应的，所以它携带了标识参数类型和返回类型的typedef成员，result_type first_argument_type    second_argument_type
    //使得，plus<int>对象的返回值被标识为plus<int> :: result_type    变成了int的typedef别名，所以函数的自适应就让函数适配器可以使用函数对象，并认为存在这些别名成员
    //接受一个自适应函数符参数的函数可以使用result_type成员来声明一个与函数返回类型匹的变量
    transform(gr8.begin() , gr8.end() , m8.begin() , Sum.begin() , plus<double>());
    cout<<"sum:\t";
    for_each(Sum.begin() , Sum.end() , show);
    cout<<endl;

    vector<double> prod (LIM);
    cout<<"prod : \t";
    transform(gr8.begin() , gr8.end() , prod.begin() , bind1st(multiplies<double>() , 2.5));//multipilies函数使得可以执行乘法，但是是二元函数，因此需要一个函数适配器，转换接受两个参数的函数符为接收一个
    //bind1st将自动完成这个过程，funtor.cpp里提供了另一种办法
    //这个过程是：bind1st(f2 , val) f1;           单个函数调用f1(x)时，返回的值与将val作为第一参数，f1()作为第二参数调用的值相同：f1(x) == f2(val ,x) ,这样就实现了将一个二元函数f2转化为一元函数f1
    for_each(prod.begin() , prod.end() ,show);




    return 0;
}


void show (double v){
    std::cout.width(6);
    std::cout.fill('*');
    std::cout << v << "   ";
}
