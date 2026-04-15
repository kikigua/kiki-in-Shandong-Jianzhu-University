package 选做题三;
//线性探测法处理冲突
public class HashtableLine {
    private Student[] table;
    public int maxsize;
    public HashtableLine(int size) {
       this.table  = new Student[size];
       this.maxsize=size;

    }


    public int hash(int key) {//除留余数法哈希函数
        return key %maxsize ;
    }
    
    public void insert(Student student) {
        int key = student.hashCode();//关键字为学生的学号
        int hash= hash(key);
        while (table[hash] != null ) {
            hash++; 
        }
        table[hash] = student;
    }


    public Student seach(Student s) {
        int key=s.hashCode();                                   //转换关键字
        int hash = hash(key);
        while (table[hash] != null) {           //不为空
            if (table[hash].hashCode() == key) {
                return s;
            }
            else hash++;
         
        }
        return null;
    }
    public void display() {
        for (int i=0;i<maxsize;i++) {                        //遍历table,输出所有student
            if (table[i]!=null) {
                System.out.println("table["+i+"]"+table[i]);
            }
        }
    }
    public boolean contain(Student element) throws Exception {//以查找结果判断哈希表是否包含指定对象
    	//若包含返回true，否则返回false
        return this.seach(element) != null;
    }
}
