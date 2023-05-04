`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 25.02.2020 16:12:06
// Design Name: 
// Module Name: vsevenseg_tb
// Project Name: 
// Target Devices: 
// Tool Versions: 
// Description: 
// 
// Dependencies: 
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
//////////////////////////////////////////////////////////////////////////////////


module vsevenseg_tb(
    );
    
    	// Inputs injected to UUT
	reg [3:0] x;

	// Outputs observed from UUT
	wire [6:0] seg_L;
	wire [3:0] digit;

	// Instantiate the Unit Under Test (UUT)
	// module name and port names must follow design source
	vsevenseg uut1 (
		.x(x), 
		.seg_L(seg_L)
	);

    	seg2digit uut2(
        	.seg_L(seg_L),
        	.digit(digit)
   	 );

	initial begin
		// Initialize Inputs
		x = 0;

		// Wait 100 ns for global reset to finish
		#100;
        
		// Add stimulus here
		// test vectors for 7-segment decoder
		// Set 4-bit input to a new value every 10 time units

		#10 x = 4'h0;		// x = 0000 (binary)
		#10 x = 4'h1;		// x = 0001 (binary)
		#10 x = 4'h2;			
		#10 x = 4'h3;			
		#10 x = 4'h4;			
		#10 x = 4'h5;			
		#10 x = 4'h6;			
		#10 x = 4'h7;			
		#10 x = 4'h8;			
		#10 x = 4'h9;					
		#10 x = 4'ha;			
		#10 x = 4'hb;			
		#10 x = 4'hc;			
		#10 x = 4'hd;			
		#10 x = 4'he;			
		#10 x = 4'hf;		// x = 1111 (binary)

		#10 ;						
	end
endmodule


