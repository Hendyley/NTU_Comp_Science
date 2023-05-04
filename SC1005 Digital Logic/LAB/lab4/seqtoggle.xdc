# Clock signal
set_property PACKAGE_PIN W5 [get_ports clk]							
	set_property IOSTANDARD LVCMOS33 [get_ports clk]
	create_clock -add -name sys_clk_pin -period 10.00 -waveform {0 5} [get_ports clk]
 
# Switches SW3-SW0
# // input b
set_property PACKAGE_PIN V17 [get_ports {b[0]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {b[0]}]
set_property PACKAGE_PIN V16 [get_ports {b[1]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {b[1]}]
set_property PACKAGE_PIN W16 [get_ports {b[2]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {b[2]}]
set_property PACKAGE_PIN W17 [get_ports {b[3]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {b[3]}]
	
# Switches SW7-SW4
# // input a
set_property PACKAGE_PIN W15 [get_ports {a[0]}]					 
	set_property IOSTANDARD LVCMOS33 [get_ports {a[0]}]          
set_property PACKAGE_PIN V15 [get_ports {a[1]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {a[1]}]
set_property PACKAGE_PIN W14 [get_ports {a[2]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {a[2]}]
set_property PACKAGE_PIN W13 [get_ports {a[3]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {a[3]}]

# Switches SW15-SW8	
#set_property PACKAGE_PIN V2 [get_ports {sw[8]}]					
	#set_property IOSTANDARD LVCMOS33 [get_ports {sw[8]}]
#set_property PACKAGE_PIN T3 [get_ports {sw[9]}]					
	#set_property IOSTANDARD LVCMOS33 [get_ports {sw[9]}]
#set_property PACKAGE_PIN T2 [get_ports {sw[10]}]					
	#set_property IOSTANDARD LVCMOS33 [get_ports {sw[10]}]
#set_property PACKAGE_PIN R3 [get_ports {sw[11]}]					
	#set_property IOSTANDARD LVCMOS33 [get_ports {sw[11]}]
#set_property PACKAGE_PIN W2 [get_ports {sw[12]}]					
	#set_property IOSTANDARD LVCMOS33 [get_ports {sw[12]}]
#set_property PACKAGE_PIN U1 [get_ports {sw[13]}]					
	#set_property IOSTANDARD LVCMOS33 [get_ports {sw[13]}]
#set_property PACKAGE_PIN T1 [get_ports {sw[14]}]					
	#set_property IOSTANDARD LVCMOS33 [get_ports {sw[14]}]
#set_property PACKAGE_PIN R2 [get_ports {sw[15]}]					
	#set_property IOSTANDARD LVCMOS33 [get_ports {sw[15]}]


# LEDs
#set_property PACKAGE_PIN U16 [get_ports {oflow}]					
	#set_property IOSTANDARD LVCMOS33 [get_ports {oflow}]
#set_property PACKAGE_PIN E19 [get_ports {LED[1]}]					
	#set_property IOSTANDARD LVCMOS33 [get_ports {LED[1]}]
#set_property PACKAGE_PIN U19 [get_ports {LED[2]}]					
	#set_property IOSTANDARD LVCMOS33 [get_ports {LED[2]}]
#set_property PACKAGE_PIN V19 [get_ports {LED[3]}]					
	#set_property IOSTANDARD LVCMOS33 [get_ports {LED[3]}]
#set_property PACKAGE_PIN W18 [get_ports {LED[4]}]					
	#set_property IOSTANDARD LVCMOS33 [get_ports {LED[4]}]
#set_property PACKAGE_PIN U15 [get_ports {LED[5]}]					
	#set_property IOSTANDARD LVCMOS33 [get_ports {LED[5]}]
#set_property PACKAGE_PIN U14 [get_ports {LED[6]}]					
	#set_property IOSTANDARD LVCMOS33 [get_ports {LED[6]}]
#set_property PACKAGE_PIN V14 [get_ports {LED[7]}]					
	#set_property IOSTANDARD LVCMOS33 [get_ports {LED[7]}]
#set_property PACKAGE_PIN V13 [get_ports {LED[8]}]					
	#set_property IOSTANDARD LVCMOS33 [get_ports {LED[8]}]
	

# Buttons // active high - press to produce 1
# // use btnC as RESET
set_property PACKAGE_PIN U18 [get_ports rst]						
	set_property IOSTANDARD LVCMOS33 [get_ports rst]
# // use btnU as sel               
set_property PACKAGE_PIN T18 [get_ports sel]						
	set_property IOSTANDARD LVCMOS33 [get_ports sel]
#set_property PACKAGE_PIN W19 [get_ports btnL]						
	#set_property IOSTANDARD LVCMOS33 [get_ports btnL]
#set_property PACKAGE_PIN T17 [get_ports btnR]						
	#set_property IOSTANDARD LVCMOS33 [get_ports btnR]
#set_property PACKAGE_PIN U17 [get_ports btnD]						
	#set_property IOSTANDARD LVCMOS33 [get_ports btnD]
 

#7 segment display // active low segments
set_property PACKAGE_PIN W7 [get_ports {seg_L[0]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {seg_L[0]}]
set_property PACKAGE_PIN W6 [get_ports {seg_L[1]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {seg_L[1]}]
set_property PACKAGE_PIN U8 [get_ports {seg_L[2]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {seg_L[2]}]
set_property PACKAGE_PIN V8 [get_ports {seg_L[3]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {seg_L[3]}]
set_property PACKAGE_PIN U5 [get_ports {seg_L[4]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {seg_L[4]}]
set_property PACKAGE_PIN V5 [get_ports {seg_L[5]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {seg_L[5]}]
set_property PACKAGE_PIN U7 [get_ports {seg_L[6]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {seg_L[6]}]

#7 segment display // Decimal Point
#set_property PACKAGE_PIN V7 [get_ports dp]							
	#set_property IOSTANDARD LVCMOS33 [get_ports dp]

# 7-segment display // active low digit anodes
set_property PACKAGE_PIN U2 [get_ports {anode_L[0]}]					
        set_property IOSTANDARD LVCMOS33 [get_ports {anode_L[0]}]
set_property PACKAGE_PIN U4 [get_ports {anode_L[1]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {anode_L[1]}]
set_property PACKAGE_PIN V4 [get_ports {anode_L[2]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {anode_L[2]}]
set_property PACKAGE_PIN W4 [get_ports {anode_L[3]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {anode_L[3]}]


## Configuration options, can be used for all designs
set_property CONFIG_VOLTAGE 3.3 [current_design]
set_property CFGBVS VCCO [current_design]
