package stack_01;

public class Palindrome {
    public static boolean isPalindrome(String str){
        LinkedStack<Character> a=new LinkedStack<>();
        for (int i=0;i<str.length();i++){
            a.push(str.charAt(i));
        }
        for (int i=0;i<str.length();i++){
            if (a.peek()==str.charAt(i) )
                a.pop();
            else
                break;
        }
        if (a.isEmpty()){
            return true;
        }
        else {
            return false;
        }
    }

    public static void main(String[] args) {
        String str="abcdcbd";
        boolean b=Palindrome.isPalindrome(str);
        System.out.println(b);
        String str1="abccba";
        boolean c=Palindrome.isPalindrome(str1);
        System.out.println(c);

    }
}
