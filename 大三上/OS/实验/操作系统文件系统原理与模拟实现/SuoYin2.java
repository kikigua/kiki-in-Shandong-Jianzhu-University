package shiyan4;
import java.util.Scanner;
public class SuoYin2 {
        static int Psize;     
        static int PNsize;  
        static int Fsize;    
        static int kuai;       
        static int offset;     
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入每个盘块的大小（单位：字节）：");
        Psize = sc.nextInt();
        System.out.println("请输入每个盘块号所占空间的大小（单位：字节）：");
        PNsize = sc.nextInt();
        System.out.println("请输入文件的大小（单位：字节）：");
        Fsize = sc.nextInt();
        if(Fsize <= 0){
            System.out.println("所输入的文件大小不合法，请重新输入");
        }else if(Fsize <= 10*Psize && Fsize > 0){    //直接块
            kuai = Fsize / Psize;
            offset = Fsize % Psize;
            System.out.println("启动了直接索引,文件占用了直接索引的地址块是第"+kuai+"块，偏移量是"+offset);
        }else if(Fsize > 10*Psize && Fsize <= (10*Psize +
                Psize*(Psize/PNsize))){  
            kuai = (Fsize - Psize*10)/Psize;
            offset = (Fsize - Psize*10)%Psize;
            System.out.println("启动了一级索引,文件占用了一级索引的地址块是第"+kuai+"块，偏移量是"+offset);
        }else if(Fsize > (10*Psize+Psize*(Psize/PNsize)) && Fsize
                <= (10*Psize+Psize*(Psize/PNsize) +
                Psize*(Psize/PNsize)*(Psize/PNsize))){      
            kuai = (Fsize - Psize*10-Psize*(Psize/PNsize))/Psize;
            offset = (Fsize - Psize*10-Psize*(Psize/PNsize))%Psize;
            System.out.println("启动了二级索引,文件占用了二级索引的地址块是第"+kuai+"块，偏移量是"+offset);
        }else if(Fsize > (10*Psize+Psize*(Psize/PNsize) +
                        Psize*(Psize/PNsize)*(Psize/PNsize)) && Fsize <=
                        (10*Psize+Psize*(Psize/PNsize)+Psize*(Psize/PNsize)*(Psize/PNsize) +
                Psize*(Psize/PNsize)*(Psize/PNsize)*(Psize/PNsize))){
            kuai = (Fsize - Psize*10-Psize*(Psize/PNsize) -
                    Psize*(Psize/PNsize)*(Psize/PNsize))/Psize;
            offset = (Fsize - Psize*10-Psize*(Psize/PNsize) -
                    Psize*(Psize/PNsize)*(Psize/PNsize))%Psize;
            System.out.println("启动了三级索引,文件占用了三级索引的地址块是第"+kuai+"块，偏移量是"+offset);
        }
    }
}

