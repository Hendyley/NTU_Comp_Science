// Copyright 1986-2017 Xilinx, Inc. All Rights Reserved.
// --------------------------------------------------------------------------------
// Tool Version: Vivado v.2017.3 (win64) Build 2018833 Wed Oct  4 19:58:22 MDT 2017
// Date        : Tue Mar 31 10:42:11 2020
// Host        : sce-Douglas1 running 64-bit Service Pack 1  (build 7601)
// Design      : seg2digit
// Purpose     : This verilog netlist is a functional simulation representation of the design and should not be modified
//               or synthesized. This netlist cannot be used for SDF annotated simulation.
// Device      : xc7a35tcpg236-1
// --------------------------------------------------------------------------------
`timescale 1 ps / 1 ps

module seg2digit
   (seg_L,
    digit);
  input [6:0]seg_L;
  output [3:0]digit;

  wire [3:0]digit;
  wire [3:0]digit_OBUF;
  wire \digit_OBUF[0]_inst_i_2_n_0 ;
  wire \digit_OBUF[0]_inst_i_3_n_0 ;
  wire \digit_OBUF[1]_inst_i_2_n_0 ;
  wire \digit_OBUF[1]_inst_i_3_n_0 ;
  wire \digit_OBUF[2]_inst_i_2_n_0 ;
  wire \digit_OBUF[2]_inst_i_3_n_0 ;
  wire \digit_OBUF[3]_inst_i_2_n_0 ;
  wire \digit_OBUF[3]_inst_i_3_n_0 ;
  wire [6:0]seg_L;
  wire [6:0]seg_L_IBUF;

  OBUF \digit_OBUF[0]_inst 
       (.I(digit_OBUF[0]),
        .O(digit[0]));
  MUXF7 \digit_OBUF[0]_inst_i_1 
       (.I0(\digit_OBUF[0]_inst_i_2_n_0 ),
        .I1(\digit_OBUF[0]_inst_i_3_n_0 ),
        .O(digit_OBUF[0]),
        .S(seg_L_IBUF[2]));
  LUT6 #(
    .INIT(64'h00000006C0000064)) 
    \digit_OBUF[0]_inst_i_2 
       (.I0(seg_L_IBUF[0]),
        .I1(seg_L_IBUF[4]),
        .I2(seg_L_IBUF[5]),
        .I3(seg_L_IBUF[6]),
        .I4(seg_L_IBUF[3]),
        .I5(seg_L_IBUF[1]),
        .O(\digit_OBUF[0]_inst_i_2_n_0 ));
  LUT6 #(
    .INIT(64'h0008000000000008)) 
    \digit_OBUF[0]_inst_i_3 
       (.I0(seg_L_IBUF[3]),
        .I1(seg_L_IBUF[1]),
        .I2(seg_L_IBUF[4]),
        .I3(seg_L_IBUF[5]),
        .I4(seg_L_IBUF[0]),
        .I5(seg_L_IBUF[6]),
        .O(\digit_OBUF[0]_inst_i_3_n_0 ));
  OBUF \digit_OBUF[1]_inst 
       (.I(digit_OBUF[1]),
        .O(digit[1]));
  MUXF7 \digit_OBUF[1]_inst_i_1 
       (.I0(\digit_OBUF[1]_inst_i_2_n_0 ),
        .I1(\digit_OBUF[1]_inst_i_3_n_0 ),
        .O(digit_OBUF[1]),
        .S(seg_L_IBUF[1]));
  LUT6 #(
    .INIT(64'h0000000440010400)) 
    \digit_OBUF[1]_inst_i_2 
       (.I0(seg_L_IBUF[0]),
        .I1(seg_L_IBUF[5]),
        .I2(seg_L_IBUF[6]),
        .I3(seg_L_IBUF[4]),
        .I4(seg_L_IBUF[3]),
        .I5(seg_L_IBUF[2]),
        .O(\digit_OBUF[1]_inst_i_2_n_0 ));
  LUT6 #(
    .INIT(64'h0000000101010001)) 
    \digit_OBUF[1]_inst_i_3 
       (.I0(seg_L_IBUF[5]),
        .I1(seg_L_IBUF[6]),
        .I2(seg_L_IBUF[4]),
        .I3(seg_L_IBUF[3]),
        .I4(seg_L_IBUF[2]),
        .I5(seg_L_IBUF[0]),
        .O(\digit_OBUF[1]_inst_i_3_n_0 ));
  OBUF \digit_OBUF[2]_inst 
       (.I(digit_OBUF[2]),
        .O(digit[2]));
  MUXF7 \digit_OBUF[2]_inst_i_1 
       (.I0(\digit_OBUF[2]_inst_i_2_n_0 ),
        .I1(\digit_OBUF[2]_inst_i_3_n_0 ),
        .O(digit_OBUF[2]),
        .S(seg_L_IBUF[0]));
  LUT6 #(
    .INIT(64'h100000020000088A)) 
    \digit_OBUF[2]_inst_i_2 
       (.I0(seg_L_IBUF[1]),
        .I1(seg_L_IBUF[2]),
        .I2(seg_L_IBUF[3]),
        .I3(seg_L_IBUF[6]),
        .I4(seg_L_IBUF[5]),
        .I5(seg_L_IBUF[4]),
        .O(\digit_OBUF[2]_inst_i_2_n_0 ));
  LUT6 #(
    .INIT(64'h0000100100040000)) 
    \digit_OBUF[2]_inst_i_3 
       (.I0(seg_L_IBUF[6]),
        .I1(seg_L_IBUF[3]),
        .I2(seg_L_IBUF[1]),
        .I3(seg_L_IBUF[2]),
        .I4(seg_L_IBUF[4]),
        .I5(seg_L_IBUF[5]),
        .O(\digit_OBUF[2]_inst_i_3_n_0 ));
  OBUF \digit_OBUF[3]_inst 
       (.I(digit_OBUF[3]),
        .O(digit[3]));
  MUXF7 \digit_OBUF[3]_inst_i_1 
       (.I0(\digit_OBUF[3]_inst_i_2_n_0 ),
        .I1(\digit_OBUF[3]_inst_i_3_n_0 ),
        .O(digit_OBUF[3]),
        .S(seg_L_IBUF[0]));
  LUT6 #(
    .INIT(64'h0000000115000011)) 
    \digit_OBUF[3]_inst_i_2 
       (.I0(seg_L_IBUF[5]),
        .I1(seg_L_IBUF[6]),
        .I2(seg_L_IBUF[3]),
        .I3(seg_L_IBUF[2]),
        .I4(seg_L_IBUF[1]),
        .I5(seg_L_IBUF[4]),
        .O(\digit_OBUF[3]_inst_i_2_n_0 ));
  LUT6 #(
    .INIT(64'h0100000100000100)) 
    \digit_OBUF[3]_inst_i_3 
       (.I0(seg_L_IBUF[4]),
        .I1(seg_L_IBUF[6]),
        .I2(seg_L_IBUF[3]),
        .I3(seg_L_IBUF[5]),
        .I4(seg_L_IBUF[2]),
        .I5(seg_L_IBUF[1]),
        .O(\digit_OBUF[3]_inst_i_3_n_0 ));
  IBUF \seg_L_IBUF[0]_inst 
       (.I(seg_L[0]),
        .O(seg_L_IBUF[0]));
  IBUF \seg_L_IBUF[1]_inst 
       (.I(seg_L[1]),
        .O(seg_L_IBUF[1]));
  IBUF \seg_L_IBUF[2]_inst 
       (.I(seg_L[2]),
        .O(seg_L_IBUF[2]));
  IBUF \seg_L_IBUF[3]_inst 
       (.I(seg_L[3]),
        .O(seg_L_IBUF[3]));
  IBUF \seg_L_IBUF[4]_inst 
       (.I(seg_L[4]),
        .O(seg_L_IBUF[4]));
  IBUF \seg_L_IBUF[5]_inst 
       (.I(seg_L[5]),
        .O(seg_L_IBUF[5]));
  IBUF \seg_L_IBUF[6]_inst 
       (.I(seg_L[6]),
        .O(seg_L_IBUF[6]));
endmodule





// dummy primatives
module OBUF (input I, output O);
    assign O=I;
endmodule

module OBUF (input I, output O);
    assign O=I;
endmodule

module MUXF7 (input I0, I1, S, output O);
    assign O=(S)?I1:I0;
endmodule

module LUT6 #(parameter INIT=64'h0000000000000000) (input I0, I1, I2, I3, I4, I5, output O);
    wire [63:0] tmp;
    assign tmp=INIT;
    assign O=tmp[{I5, I4, I3, I2, I1, I0}];
endmodule

/*
module LUT5 #(parameter INIT=32'h00000000) (input I0, I1, I2, I3, I4, output O);
    wire [31:0] tmp;
    assign tmp=INIT;
    assign O=tmp[{I4, I3, I2, I1, I0}];
endmodule
*/