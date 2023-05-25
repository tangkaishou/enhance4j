package cn.tanglaoer.guava.utilities;

import com.google.common.base.MoreObjects;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/22
 */
public class ObjectsTest {
    static class Guava  {
        private final String manufacturer;

        public Guava(String manufacturer) {
            this.manufacturer = manufacturer;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this).omitNullValues()
                    .add("manufacturer", this.manufacturer).toString();
        }


    }
}
