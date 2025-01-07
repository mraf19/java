package programmer.zaman.now.generic;

public class MyData<T> {

    private T Data;

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public MyData(T data) {
        Data = data;
    }
}
