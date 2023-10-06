package com.example.cgm;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;

import java.awt.*;
import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.scene.control.Label;
import javafx.scene.control.Button;



import com.sun.management.OperatingSystemMXBean;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class HardwareMonitorController implements Initializable {
    @FXML
    private ProgressBar cpuProgressBar;


    @FXML
    private ImageView HardwareView;

    @FXML
    private Label cpuLabel;

    @FXML
    private ProgressBar memoryProgressBar;

    @FXML
    private Label memoryLabel;

    @FXML
    private Button refreshButton;

    @FXML
    private Button btnHardwareShow;

    @FXML
    private AnchorPane pfDetails;

    @FXML
    private Label lblProcessor;

    @FXML
    private Label lblRam;

    @FXML
    private Label lblGPU;

    @FXML
    private Label lblFan1;

    @FXML
    private Label lblFan2;

    @FXML
    private Label lblMemoryDetails;

    @FXML
    private Label lblPowerPlan;

    @FXML
    private Label lblTemperature;

    @FXML
    private TextArea detailsTextArea;


    private ScheduledExecutorService executorService;

    private final double MAX_CPU_USAGE = 100.0;

    private final double MAX_MEMORY_USAGE = 100.0;

    private OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    @Override
    public void initialize(URL url, ResourceBundle rb){
        // Initialize the user interface components
        cpuProgressBar.setProgress(0);
        memoryProgressBar.setProgress(0);

        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::updateHardwareMonitorData, 0, 1, TimeUnit.SECONDS);

        File HardwareFile = new File("Image/Hardware_monitor/hardware.png");
        Image HardwareImage = new Image(HardwareFile.toURI().toString());
        HardwareView.setImage(HardwareImage);



    }


    @FXML
    public void handleRefreshButton(javafx.event.ActionEvent event) {
        updateHardwareMonitorData();
    }

    private void updateHardwareMonitorData() {
        // Get the hardware monitoring data and update the user interface components
        double cpuUsage = getCPUUsage();
        Platform.runLater(() -> {
            cpuProgressBar.setProgress(cpuUsage / MAX_CPU_USAGE);
            cpuLabel.setText(String.format("%.2f%%", cpuUsage));
        });

        double memoryUsage = getMemoryUsage();
        Platform.runLater(() -> {
            memoryProgressBar.setProgress(memoryUsage / MAX_MEMORY_USAGE);
            memoryLabel.setText(String.format("%.2f%%", memoryUsage));
        });

    }




    private double getCPUUsage() {
        // Use JMX to get the CPU usage
        // Implement your own logic here

        double cpuUsage = osBean.getProcessCpuLoad();

        // Convert the CPU usage to a percentage
        cpuUsage = cpuUsage * 100;

        // Format the CPU usage to two decimal places
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        cpuUsage = Double.parseDouble(decimalFormat.format(cpuUsage));

        return cpuUsage;
    }

    private double getMemoryUsage() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryBean.getHeapMemoryUsage();
        MemoryUsage nonHeapMemoryUsage = memoryBean.getNonHeapMemoryUsage();

        // Get the used memory in megabytes
        double usedMemory = (heapMemoryUsage.getUsed() + nonHeapMemoryUsage.getUsed()) / (1024 * 1024);

        // Get the maximum memory in megabytes
        double maxMemory = (heapMemoryUsage.getMax() + nonHeapMemoryUsage.getMax()) / (1024 * 1024);

        // Calculate the memory usage as a percentage
        double memoryUsage = (usedMemory / maxMemory) * 100;

        // Format the memory usage to two decimal places
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        memoryUsage = Double.parseDouble(decimalFormat.format(memoryUsage));

        return memoryUsage;
    }



}
