import java.util.Scanner;

public class Chall1_2 {

    private static String evaluate(String input, boolean a, boolean b, boolean c, boolean d){
        
        input=input.replaceAll(" ", "");

        input=input.replaceAll("a", a ? "T" : "F");
        input=input.replaceAll("b", b ? "T" : "F");
        input=input.replaceAll("c", c ? "T" : "F");
        input=input.replaceAll("d", d ? "T" : "F");

        while(input.indexOf("(")!=-1){
            input=input.replace(input.substring(input.indexOf("("), input.indexOf(")")+1),evaluate(input.substring(input.indexOf("(")+1, input.indexOf(")")),a,b,c,d));
        }

        while(input.indexOf("¬")!=-1){
            input=input.replace("¬T","F");
            input=input.replace("¬F","T");
        }
        
        while(input.indexOf("∧")!=-1){
            input=input.replace("T∧T","T");
            input=input.replace("F∧F","F");
            input=input.replace("T∧F","F");
            input=input.replace("F∧T","F");
        }
        
        while(input.indexOf("∨")!=-1){
            input=input.replace("F∨F","F");
            input=input.replace("T∨T","T");
            input=input.replace("T∨F","T");
            input=input.replace("F∨T","T");
        }

        while(input.indexOf("⊕")!=-1){
            input=input.replace("F⊕F","F");
            input=input.replace("T⊕T","F");
            input=input.replace("T⊕F","T");
            input=input.replace("F⊕T","T");
        }

        while(input.indexOf("→")!=-1){
            input=input.replace("T→F","F");
            input=input.replace("T→T","T");
            input=input.replace("F→F","T");
            input=input.replace("F→T","T");
        }

        while(input.indexOf("↔")!=-1){
            input=input.replace("F↔F","T");
            input=input.replace("T↔T","T");
            input=input.replace("T↔F","F");
            input=input.replace("F↔T","F");
        }

        return input;
    }

    private static void print(String input){
        boolean a=false;
        boolean b=false;
        boolean c=false;
        boolean d=false;
        System.out.println("a b c d | e");
        System.out.println("------------");
        String dnf="(";
        for(int i=0;i<16;i++){
            String ex=evaluate(input,a,b,c,d);
            if(ex.equals("T")){
                if(!dnf.equals("(")){
                    dnf=dnf+" ∨ (";
                }
                dnf=dnf+(a?"a":"¬a")+" ∧ "+(b?"b":"¬b")+" ∧ v"+(c?"c":"¬c")+" ∧ "+(d?"d":"¬d")+")";
            }
            System.out.println((a?"T":"F")+" "+(b?"T":"F")+" "+(c?"T":"F")+" "+(d?"T":"F")+" "+ex);
            d=!d;
            if(i%2==1){
                c=!c;
            }
            if(i%4==3){
                b=!b;
            }
            if(i%8==7){
                a=!a;
            }
        }
        System.out.println(dnf);
    }
    public static void main(String[] args){
        String input="(¬a ∧ b ∧ ¬c ∧ d) ∨ (¬a ∧ b ∧ c ∧ d) ∨ (a ∧ b ∧ c ∧ d)";
        System.out.println("Please input an expression:");
        Scanner a= new Scanner(System.in);
        String input1=a.nextLine();
        print(input);
    }
} 
