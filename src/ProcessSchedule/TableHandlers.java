/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcessSchedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Pendoragon
 */
public class TableHandlers {        
    /**
     * Sets the table in accordance to the type of process schedule algorithm selected and count
     * @param type the process type
     * @param row_count the number of rows of the table
     * @param table the table to be set.
     */
    public static void setTable(String type, int row_count, JTable table) {
        String[] columnNames = new String[]{"Process No.", "AT", "BT", "CT", "TAT", "WT"};
        String[] columnNamesWithPriority = new String[]{"Process No.", "AT", "BT", "Priority", "CT", "TAT", "WT"};
        JTableHeader tableHeader = table.getTableHeader(); // Get table header
        TableColumnModel tableColumnModel = tableHeader.getColumnModel(); // Get table column model
        int col_count; // Assignments done in switch case
        
        switch (type) {
            case "First Come First Serve":
                col_count = columnNames.length;
                table.setModel(new DefaultTableModel(row_count, col_count));                   
                for (int i = 0; i < col_count; i++) {
                    tableColumnModel.getColumn(i).setHeaderValue(columnNames[i]);
                }
                break;
            case "Shortest Job First":
                col_count = columnNames.length;
                table.setModel(new DefaultTableModel(row_count, col_count));
                for (int i = 0; i < col_count; i++) {
                    tableColumnModel.getColumn(i).setHeaderValue(columnNames[i]);
                }
                break;
            case "Shortest Remaining Time First":
                col_count = columnNames.length;
                table.setModel(new DefaultTableModel(row_count, col_count));
                for (int i = 0; i < col_count; i++) {
                    tableColumnModel.getColumn(i).setHeaderValue(columnNames[i]);
                }
                break;
            case "Non-Preemptive Priority":
                col_count = columnNamesWithPriority.length;
                table.setModel(new DefaultTableModel(row_count, col_count));
                for (int i = 0; i < col_count; i++) {
                    tableColumnModel.getColumn(i).setHeaderValue(columnNamesWithPriority[i]);
                }
                break;
            case "Preemptive Priority":
                col_count = columnNamesWithPriority.length;
                table.setModel(new DefaultTableModel(row_count, col_count));
                for (int i = 0; i < col_count; i++) {
                    tableColumnModel.getColumn(i).setHeaderValue(columnNamesWithPriority[i]);
                }
                break;
            case "Round Robin":
                col_count = columnNames.length;
                table.setModel(new DefaultTableModel(row_count, col_count));
                for (int i = 0; i < col_count; i++) {
                    tableColumnModel.getColumn(i).setHeaderValue(columnNames[i]);
                }
                break;
            default:
                break;
        }
        tableHeader.repaint();
        table.getTableHeader().setReorderingAllowed(false); // Disable table column dragging.
        centerTableHorizontalAlignment(table);
        clearTable(table);
        setProcessNumber(table);                
    }
    
    public static void setGanttChart(JTable table) {
        table.getTableHeader().setReorderingAllowed(false); // Disable table column dragging.
    }

    // Sets the labels above the table in accordance to the type of process schedule algorithm selected and count
    public static void setLabels(String type, JLabel typeLabel, JLabel countLabel, JLabel modeLabel, JLabel criterionLabel, JComboBox typeComboBox, JComboBox countComboBox) {
        typeLabel.setText(typeComboBox.getSelectedItem().toString());
        countLabel.setText((String) countComboBox.getSelectedItem());
        
        switch (type) {
            case "First Come First Serve":
                modeLabel.setText("Non-preemptive");
                criterionLabel.setText("Arrival time (AT)");
                break;
            case "Shortest Job First":
                modeLabel.setText("Non-preemptive");
                criterionLabel.setText("Burst time (BT)");
                break;
            case "Shortest Remaining Time First":
                modeLabel.setText("Preemptive");
                criterionLabel.setText("Burst time (BT)");
                break;
            case "Non-Preemptive Priority":
                modeLabel.setText("Non-preemptive");
                criterionLabel.setText("Priority");
                break;
            case "Preemptive Priority":
                modeLabel.setText("Preemptive");
                criterionLabel.setText("Priority");
                break;
            case "Round Robin":
                modeLabel.setText("Preemptive");
                criterionLabel.setText("Arrival time (AT)");
                break;
            default:
                break;
        }
    }
    
    /**
     * Sets the horizontal alignment of elements in a table to the center
     * @param table the table to be modified.
     */
    public static void centerTableHorizontalAlignment(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
    
    // Set the Process number to incrementing order.
    public static void setProcessNumber(JTable table) {
        for (int i = 0; i < table.getRowCount(); i++) {
            table.setValueAt(i + 1, i, 0);
        }
    }
    
    // Clears all table cell
    public static void clearTable(JTable table) {
        for(int j = 0; j < table.getColumnCount(); j++) {
            for(int i = 0; i < table.getRowCount(); i++) {
                table.setValueAt("", i, j);
            }
        }
    }
    
    // Shuffles a table column
    public static void shuffleTableColumnDouble(JTable table, int column) {
        ArrayList<Double> list = new ArrayList<>();
        
        for(int i = 0; i < table.getRowCount(); i++) {
           list.add((Double) table.getValueAt(i, column));
        }        
        
        Collections.shuffle(list);  // Shuffle the list
        
        for(int i = 0; i < table.getRowCount(); i++) {
            table.setValueAt(list.get(i), i, column);
        }
    }
    
    // Shuffles a table column
    public static void shuffleTableColumnInt(JTable table, int column) {
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int i = 0; i < table.getRowCount(); i++) {
           list.add((Integer) table.getValueAt(i, column));
        }        
        
        Collections.shuffle(list);  // Shuffle the list
        
        for(int i = 0; i < table.getRowCount(); i++) {
            table.setValueAt(list.get(i), i, column);
        }
    }
    
    // Returns the table cell value into double.
    public static double tableValueToDouble(JTable table, int row, int col) {
        return Double.parseDouble(String.valueOf(table.getValueAt(row, col)));
    }
    
    // Returns the table cell value into int.
    public static int tableValueToInteger(JTable table, int row, int col) {
        return Integer.parseInt(String.valueOf(table.getValueAt(row, col)));
    }
    
    public static void insertIntoTable(JTable table, String string) {                
        StringTokenizer stringTokenizer = new StringTokenizer(string, "\n"); // Used as a condition in the for loop, to know if there are still more columns.

        String rowString, value;

        for(int i = 0; stringTokenizer.hasMoreTokens(); i++) {
            rowString = stringTokenizer.nextToken();                
            StringTokenizer stringTokenizer2 = new StringTokenizer(rowString, "\t"); // Used as a condition in the for loop, to know if there are more element in the row.

            for(int j = 0; stringTokenizer2.hasMoreTokens(); j++) {
                value = (String)stringTokenizer2.nextToken(); // Store the next token (which are the values in the cell) in the string value.                
                
                if(i < table.getRowCount() && j < table.getColumnCount()) {
                    if(j == 3 && table.getColumnCount() != 7) {
                        value = (String)stringTokenizer2.nextToken();
                    }
                    table.setValueAt(value.trim(), i, j);
                }                    
            }
        }
    }

    public static LinkedList<Process> addValuesToProcess(String processType, JTable table) {
        LinkedList<Process> plist = new LinkedList<>();
        switch (processType) {
            case "First Come First Serve":
            case "Shortest Job First":
            case "Shortest Remaining Time First":
            case "Round Robin":
                for (int i = 0; i < table.getRowCount(); i++) {
                    plist.add(new Process(tableValueToInteger(table, i, 0), TableHandlers.tableValueToDouble(table, i, 1), TableHandlers.tableValueToDouble(table, i, 2)));
                }
                break;
            case "Non-Preemptive Priority":
            case "Preemptive Priority":
                for (int i = 0; i < table.getRowCount(); i++) {
                    plist.add(new Process(tableValueToInteger(table, i, 0), TableHandlers.tableValueToDouble(table, i, 1), TableHandlers.tableValueToDouble(table, i, 2), TableHandlers.tableValueToInteger(table, i, 3)));
                }
                break;            
            default:
                break;
        }
        return plist;
    }

    public static void doProcessOperation(String processType, ProcessOperation pa) {
        switch (processType) {
            case "First Come First Serve":
                pa.nonPreemptiveSchedule("firstcomefirstserve");
                break;
            case "Shortest Job First":
                pa.nonPreemptiveSchedule("shortestjobfirst");
                break;
            case "Non-Preemptive Priority":
                pa.nonPreemptiveSchedule("nppriority");
                break;
            case "Shortest Remaining Time First":
                pa.preemptiveSchedule("shortestremainingtime");
                break;
            case "Preemptive Priority":
                pa.preemptiveSchedule("ppriority");
                break;
            default:
                break;
        }
    }
    
    //Overload
    public static void doProcessOperation(String processType, ProcessOperation pa, double timeQuantum) {
        pa.roundRobinSchedule(timeQuantum);
    }
    
    public static void replaceNullWithEmpty(JTable table) {
        for(int i = 0; i < table.getColumnCount(); i++) {
            for(int j = 0; j < table.getRowCount(); j++) {
                if(table.getValueAt(j, i) != null) {
                    if (table.getValueAt(j, i).equals("null")) {
                        table.setValueAt("", j, i);
                    }
                }
            }
        }
    }  
    
    public static void validateTable(JTable table) {
        int columnLimit = table.getColumnCount() - 3;
        for(int i = 1; i < columnLimit; i++) {
            for(int j = 0; j < table.getRowCount(); j++) {                
                String value_str = String.valueOf(table.getValueAt(j, i));
                // If the type has priority and the column is equal to priority
                if(table.getColumnCount() == 7 && i == 3) {                    
                    int value = Integer.parseInt(NumericHandlers.replaceZeroAndEmptyWith(value_str, "1"));
                    table.setValueAt(value, j, i);
                } else if(i == 2) {
                    double value = Double.parseDouble(NumericHandlers.replaceZeroAndEmptyWith(value_str, "1.0"));
                    table.setValueAt(value, j, i);
                } else {
                    double value = Double.parseDouble(NumericHandlers.removeAllNonNumeric(value_str));
                    table.setValueAt(value, j, i);
                }             
            }
        }
    }
}
