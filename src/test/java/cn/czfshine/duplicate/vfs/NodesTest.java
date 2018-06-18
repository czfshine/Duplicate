package cn.czfshine.duplicate.vfs; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 

/** 
* Nodes Tester. 
* 
* @author <Authors name> 
* @since <pre>06/17/2018</pre> 
* @version 1.0 
*/ 
public class NodesTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: printNodes(Node root) 
* 
*/ 
@Test
public void testPrintNodes() throws Exception {

    Node<Long> add = new LongNode((long) 1).add((long) 2)
            .add((long) 3)
            .add(new LongNode().add((long) 4).add((long) 5)).add((long) 6);

    Nodes.printNodes(add);
} 


/** 
* 
* Method: printOne(Node root, int level) 
* 
*/ 
@Test
public void testPrintOne() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = Nodes.getClass().getMethod("printOne", Node.class, int.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
