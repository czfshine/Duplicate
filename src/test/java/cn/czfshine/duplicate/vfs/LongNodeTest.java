package cn.czfshine.duplicate.vfs; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 

/** 
* LongNode Tester. 
* 
* @author <Authors name> 
* @since <pre>06/17/2018</pre> 
* @version 1.0 
*/ 
public class LongNodeTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

@Test

public void testUsage() throws Exception{
    new LongNode((long) 1).add((long) 2)
            .add((long) 3)
            .add(new LongNode().add((long) 4).add((long) 5)).add((long) 6);
}

} 
