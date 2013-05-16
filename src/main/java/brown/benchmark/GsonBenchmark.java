package brown.benchmark;

import java.util.HashMap;
import java.util.Map;

import com.google.caliper.Benchmark;
import com.google.caliper.runner.CaliperMain;
import com.google.gson.Gson;

import org.apache.commons.lang3.builder.ToStringBuilder;

/** Benchmark Gson.toJson() versus ToStringBuilder. */
public class GsonBenchmark extends Benchmark {

    private final Gson gson = new Gson();

    private final SomeBean testObj;

    public static void main(String[] args) {
        CaliperMain.main(GsonBenchmark.class, args);
    }

    public GsonBenchmark() {
        InnerBean inner = new InnerBean();
        inner.getMap().put("abc", "def");
        inner.getMap().put("123", "456789");
        inner.getMap().put("xyz", "---asdfasdf--");
        inner.getMap().put("zyx", "");
        this.testObj = new SomeBean("blah", "bliz", 99, 1344354, inner);
    }

    public void timeGson(int reps) {
        for (int i = 0; i < reps; i++) {
            gson.toJson(testObj);
        }
    }

    public void timeCommonsToStringBuilder(int reps) {
        for (int i = 0; i < reps; i++) {
            testObj.toString();
        }
    }

    public void timeToStringManual(int reps) {
        for (int i = 0; i < reps; i++) {
            testObj.toStringIDEGenerated();
        }
    }

    public static class SomeBean {

        private final String foo;
        private final String bar;
        private final int someInt1;
        private final int someInt2;
        private final InnerBean inner;

        public SomeBean(String bar, String foo, int someInt1, int someInt2, InnerBean inner) {
            this.bar = bar;
            this.foo = foo;
            this.someInt1 = someInt1;
            this.someInt2 = someInt2;
            this.inner = inner;
        }

        public String getBar() {
            return bar;
        }

        public String getFoo() {
            return foo;
        }

        public int getSomeInt1() {
            return someInt1;
        }

        public int getSomeInt2() {
            return someInt2;
        }

        public InnerBean getInner() {
            return inner;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("bar", bar)
                    .append("foo", foo)
                    .append("someInt1", someInt1)
                    .append("someInt2", someInt2)
                    .append("inner", inner)
                    .toString();
        }

        public String toStringIDEGenerated() {
            final StringBuilder sb = new StringBuilder("SomeBean{");
            sb.append("bar='").append(bar).append('\'');
            sb.append(", foo='").append(foo).append('\'');
            sb.append(", someInt1=").append(someInt1);
            sb.append(", someInt2=").append(someInt2);
            sb.append(", inner=").append(inner.toStringIDEGenerated());
            sb.append('}');
            return sb.toString();
        }
    }

    public static class InnerBean {

        private final Map<String, String> map = new HashMap<String, String>();

        public Map<String, String> getMap() {
            return map;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("map", map)
                    .toString();
        }

        public String toStringIDEGenerated() {
            final StringBuilder sb = new StringBuilder("InnerBean{");
            sb.append("map=").append(map);
            sb.append('}');
            return sb.toString();
        }
    }
}
