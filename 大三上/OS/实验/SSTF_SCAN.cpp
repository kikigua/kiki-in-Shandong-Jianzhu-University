#include <iostream>
#include<cstdlib>
#include <time.h>

using namespace std;
class DISK
{
private:
    int Movedistance;//移动距离
    int Lable[100];//标号指针
    string Movedirection;//移动方向
public:
    int  Calculate_Diference(int a, int b)//计算移动距离（差值的绝对值）
    {
        return (a - b > 0 ? a - b : b - a);
    }
    void ShowPath(int a[], int x, int len)//调度过程  时间复杂度O(n)
    {
        cout << "从" << x << "号磁道开始" << endl;
        for (int i = 1; i < len + 1; i++)
        {
            cout << "下一个访问的磁道号：" << a[i] << "\t移动的距离：" << Calculate_Diference(a[i], a[i - 1]) << endl;
        }
    }
    void CalculateMoveDistance(int a[], int x, int len)//计算平均寻道长度  时间复杂度O(n)
    {
        double count = 0;;
        double average = 0;
        for (int i = 1; i < len + 1; i++)
        {
            count += Calculate_Diference(a[i], a[i - 1]);
        }
        average = count / len;
        cout << "平均寻道长度为：" << average << endl;
    }
//    void FIFS(int a[], int x, int len)//先来先服务算法  
//    {
//        Lable[0] = x;//数组中第一个数为当前磁道位置
//        for (int i = 0; i < len; i++)
//        {
//            Lable[i + 1] = a[i];
//        }
//        ShowPath(Lable, x, len);
//        CalculateMoveDistance(Lable, x, len);
//    }
    void SSTF(int a[], int x, int len)//最短寻道时间优先
    {
        Lable[0] = x;//数组中第一个数为当前磁道位置
        int m = 0;
        int temp = 0;
        for (int i = 0; i < len; i++)//该循环为了找出访问顺序，Lalbe[];
        {
            m = i;
            for (int j = i; j < len; j++)//遍历数组a[]，找出与当前磁头最近的一个磁道。
            {
                if (Calculate_Diference(a[j + 1], Lable[i]) < Calculate_Diference(a[m], Lable[i]))
                {
                    m = j + 1;
                    temp = a[j + 1];
                }
                else temp = a[m];
            }
            Lable[i + 1] = temp;
            int temp2 = a[m];//找出该磁道后将找到的数据置换到数组最前面，以便下一次遍历时跳过；
            a[m] = a[i];
            a[i] = temp2;
        }
        ShowPath(Lable, x, len);
        CalculateMoveDistance(Lable, x, len);
    }

    void insertsort(int a[], int len)//插入排序
    {
        int temp;
        for (int i = 1; i < len; i++)
        {
            temp = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] > temp)
            {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = temp;
        }
    }

    void SCAN(int a[], int x, int len,string  Movedirection)//扫描算法
    {
        Lable[0] = x;//数组中第一个数为当前磁道位置
        int m = 0, r = 0, l = 0, r2 = 0, l2 = 0;
        int temp = 0;
        int right[100];
        int rightelse[100];
        int left[100];
        int leftelse[100];
        for (int i = 0; i < len; i++)//磁头向磁道增大的方向移动时，将大的磁道组成一个数组，其他的磁道组成一个数组
        {
            if (Lable[0] < a[i])
            {
                right[r] = a[i];
                r++;
            }
            else rightelse[r2] = a[i], r2++;
        }
        for (int i = 0; i < len; i++)//磁头向磁道减小的方向移动时，将小的磁道组成一个数组，其他的磁道组成一个数组
        {
            if (Lable[0] > a[i])
            {
                left[l] = a[i];
                l++;
            }
            else leftelse[l2] = a[i], l2++;
        }
        if (Movedirection == "right")
        {
            insertsort(right, r);//将大的进行排序
            for (int j = 0; j < r; j++)
            {
                Lable[j + 1] = right[j];
            }
            for (int i = 0; i < r2; i++)//找剩余的
            {
                m = i;
                for (int j = i; j < r2; j++)//遍历数组a[]，找出与当前磁头最近的一个磁道。
                {
                    if (Calculate_Diference(rightelse[j + 1], Lable[i]) < Calculate_Diference(rightelse[m], Lable[i]))
                    {
                        m = j + 1;
                        temp = rightelse[j + 1];
                    }
                    else temp = rightelse[m];
                }
                Lable[i +r+ 1] = temp;
                int temp2 = rightelse[m];//找出该磁道后将找到的数据置换到数组最前面，以便下一次遍历时跳过；
                rightelse[m] = rightelse[i];
                rightelse[i] = temp2;
            }
           
        }
        if (Movedirection == "left")
        {
            int c = 1;
            insertsort(left, l);//将小的进行排序
            for (int j = l - 1; j >= 0; j--)
            {
                Lable[c] = left[j];
                c++;
            }
            for (int i = 0; i < l2; i++)//找剩余的
            {
                m = i;
                for (int j = i; j < l2; j++)//遍历数组a[]，找出与当前磁头最近的一个磁道。
                {
                    if (Calculate_Diference(leftelse[j + 1], Lable[i]) < Calculate_Diference(leftelse[m], Lable[i]))
                    {
                        m = j + 1;
                        temp = leftelse[j + 1];
                    }
                    else temp = leftelse[m];
                }
                Lable[i + l + 1] = temp;
                int temp2 = leftelse[m];//找出该磁道后将找到的数据置换到数组最前面，以便下一次遍历时跳过；
                leftelse[m] = leftelse[i];
                leftelse[i] = temp2;
            }
        }
        ShowPath(Lable, x, len);
        CalculateMoveDistance(Lable, x, len);
    }
};
int main()
{
    DISK disk;
    srand((int)time(NULL));
    cout << "请输入生成的访问磁道进程数量：";
    int n, m, p=0;
    int flag ;
    string direction;
    cin >> n;
    int a[100];
    for (int i = 0; i < n; i++)
    {
        a[i] = rand() % 1000;
    }
    cout << "生成的访问进程为：";
    for (int i = 0; i < n; i++)
    {
        if (i % 5 == 0)
        {
            cout << endl;
        }
        cout << a[i] << "  ";

    }
    cycle1:cout << "\n请输入起始位置(0-200)：";
    cin >> m;
    cycle2:cout << "请输入磁盘调度方式(1:SSTF   2:SCAN)" << endl;
    cin >> flag;
    switch (flag)
    {
    case 1:
        cout << "执行SSTF算法" << endl; disk.SSTF(a, m, n); cout << "\n\n"; break;
    case 2:
        cout << "执行SCAN" << endl;
        cout << "请确定扫描方向（向外扫描：'right',向内扫描'left')";  
        cin >> direction;
        disk.SCAN(a, m, n, direction); cout << "\n\n"; break;
    }
    cout << "\n是否继续？(1：继续    0：退出    2：重新确定初始位置";
    cin >> p;
    if (p == 1)
        goto cycle2;
    else if (p == 2)
    {
        goto cycle1;
    }
    else return 0;
}
