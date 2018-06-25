package test.cn.czfshine.duplicate.struct; 

import cn.czfshine.duplicate.struct.MulitHash;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/** 
* MulitHash Tester. 
* 
* @author <Authors name> 
* @since <pre>ÁùÔÂ 21, 2018</pre> 
* @version 1.0 
*/ 
public class MulitHashTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getHash() 
* 
*/ 
@Test
public void testGetHash() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: update(Object hash) 
* 
*/ 
@Test
public void testUpdate() throws Exception { 
//TODO: Test goes here... 

    MulitHash m = new MulitHash();
    assertEquals(m.getHash(),1);
    m.update(3);
    assertEquals(m.getHash(),3);
    m.update(0xAB0);
    assertEquals(m.getHash(),0XAB*3);

    int [] testdata={1,2,3,4,5,6,7,8,9,0,0,0,255,
            12345678,852741963,123456789,951623847,753421869,-1,-255,-65536,65536};
    MulitHash m1 = new MulitHash();
    MulitHash m2 = new MulitHash();

    for(int i=0,j=testdata.length-1;i<testdata.length;i++,j--){
        m1.update(testdata[i]);
        m2.update(testdata[j]);
    }
    MulitHash m3 = new MulitHash();
    for(int i=0;i<1000000;i++){
        m3.update(new Random().nextInt());
        assertNotEquals(m3.getHash(),0);
    }

}


} 
