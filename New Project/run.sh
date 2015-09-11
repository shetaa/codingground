javac MP1.java
if [ "$?" != "0" ]; then
    echo "[Error] compile failed!" 1>&2
    exit 1
fi
jar cfe MP1.jar MP1 MP1.class
if [ "$?" != "0" ]; then
    echo "[Error] jar failed!" 1>&2
    exit 1
fi
#java -jar MP1.jar 10545079 > output.txt
java -jar MP1.jar 0 > output0.txt
#java -jar MP1.jar 1 > output1.txt
cat output0.txt

