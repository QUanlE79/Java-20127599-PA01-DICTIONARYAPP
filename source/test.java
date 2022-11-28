public class test {
    public static void main(String[] args) {
        String[] a = {"AABC", "AAAC","AA", "AAAA","ABDE"};
        for(String i: a){
            if (i.matches("AA[ -~]+")){
                System.out.println(i);
            }
        }
    }
}
