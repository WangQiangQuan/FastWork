package fast.wq.com.fastandroid.bean;

import java.io.Serializable;

/**
 * Created by admin on 2018/2/11.
 */

public class SerializableBean implements Serializable {
    private static final long serialVersionUID = 1L;

    public int id;
    public String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
