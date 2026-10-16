import java.util.Arrays;
class Builder2{
public static void main(String[] args){
StringBuilder sb = new StringBuilder("Java");
sb.append("Programming");
sb.insert(0, "Learn");
sb.replace(6,10, "Core Java");
System.out.println(sb);
}
}