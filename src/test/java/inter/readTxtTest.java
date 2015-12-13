package inter;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class readTxtTest {
    @Test
    public void should_return_empty_when_txt_has_no_data() throws Exception {
        String path = "data/empty.txt";
        List<String> result = new readTxt().readData(path);
        assertThat(result.size(), is(0));

    }

    @Test
    public void should_return_1_when_txt_has_1_data() throws Exception {
        String path = "data/onedata.txt";
        List<String> result = new readTxt().readData(path);
        assertThat(result.size(), is(1));
        assertThat(result.get(0), is("bulabulabula"));
    }

    @Test
    public void should_return_3_when_txt_has_3_datas() throws Exception {
        String path = "data/itemlist.txt";
        List<String> result = new readTxt().readData(path);
        assertThat(result.size(), is(3));
        assertThat(result.get(0), is("ITEM000001:40"));
        assertThat(result.get(1), is("ITEM000003:50"));
        assertThat(result.get(2), is("ITEM000005:60"));
    }

}
