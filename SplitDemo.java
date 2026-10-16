import java.util.Arrays;
class SplitDemo{
public static void main(String[] args){
String data = "101,Rahul,Medium,Stored";
String[] parts = data.split(",");
for (String part : parts) {
System.out.println(part);
}
}
}