package com.epam.gyozo_karer.etl.core;

import com.epam.gyozo_karer.etl.model.Data;
import com.epam.gyozo_karer.etl.transform.SimpleParallelIdleProcess;
import com.epam.gyozo_karer.etl.transform.SimpleProcess;
import com.epam.gyozo_karer.etl.transform.SimpleSequentialIdleProcess;
import com.epam.gyozo_karer.etl.transform.container.ParallelProcessContainer;
import com.epam.gyozo_karer.etl.transform.container.SequentialProcessContainer;
import com.epam.gyozo_karer.etl.transform.container.impl.DataTypeParallelProcessContainerImpl;
import com.epam.gyozo_karer.etl.transform.container.impl.SequentialProcessContainerImpl;


/**
 * Hello world!
 *
 */
public class App {
	
    public static void main( String[] args ) {
        SequentialProcessContainer mainContainer = new SequentialProcessContainerImpl();

        SimpleProcess proc1 = new SimpleSequentialIdleProcess();
        mainContainer.addProcess(proc1);
        SimpleProcess proc2 = new SimpleSequentialIdleProcess();
        mainContainer.addProcess(proc2);

        SequentialProcessContainer seqContainer = new SequentialProcessContainerImpl();
        SimpleProcess proc3 = new SimpleSequentialIdleProcess();
        seqContainer.addProcess(proc3);
        SimpleProcess proc4 = new SimpleSequentialIdleProcess();
        seqContainer.addProcess(proc4);
        mainContainer.addProcess(seqContainer);
        
        ParallelProcessContainer parContainer = new DataTypeParallelProcessContainerImpl();
        SimpleProcess proc5 = new SimpleParallelIdleProcess();
        parContainer.addProcess(proc5);
        SimpleProcess proc6 = new SimpleParallelIdleProcess();
        parContainer.addProcess(proc6);
        mainContainer.addProcess(parContainer);
        
        mainContainer.addProcess(proc2);

        Data data = new Data();
        data.addElement("NAME1", "Value1");
        data.addElement("NAME2", "Value2");
        data.addElement("NAME3", "Value3");
        data.addElement("NAME4", "Value4");
        

        mainContainer.processing(data);
    }
}
