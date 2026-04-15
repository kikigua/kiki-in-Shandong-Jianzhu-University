/*module decoder38(A2,A1,A0,E1,E2N,E3N,Y)
     input A2,A1,A0,E1,E2N,E3N;
     output [7:0] Y;
     
     
     reg Y7,Y6,Y5,Y4,Y3,Y2,Y1,Y0;
     wire E = E1 & (!E2N) & (!E3N);
     wire [2:0] A = {A2,A1,A0};
     
     always  @(E,A2,A1,A0)
     begin
        if(E)
             case({A2,A1,A0})
             3'b000: Y=8'b1111_1110;
             3'b001: Y=8'b1111_1101;
             3'b010: Y=8'b1111_1011;
             3'b011: Y=8'b1111_0111;
             3'b100: Y=8'b1110_1111;
             3'b101: Y=8'b1101_1111;
             3'b110: Y=8'b1011_1111;
             3'b111: Y=8'b0111_1111;
             default: Y =8'b1111_1111;
             endcase
        else Y = 8'b1111_1111;
      end
 endmodule*/
 
 module decoder38(A2,A1,A0,E1,E2N,E3N,Y7,Y6,Y5,Y4,Y3,Y2,Y1,Y0);
     input A2,A1,A0,E1,E2N,E3N;
     output Y7,Y6,Y5,Y4,Y3,Y2,Y1,Y0;
     
     reg Y7,Y6,Y5,Y4,Y3,Y2,Y1,Y0;
     wire E = E1 & (!E2N) & (!E3N);
     wire [2:0] A = {A2,A1,A0};
     
     always  @(E,A)
     begin
        if(E)
             case(A)
             3'b000:begin Y7=1'b1; Y6=1'b1; Y5=1'b1; Y4=1'b1; Y3=1'b1; Y2=1'b1; Y1=1'b1; Y0=1'b0;end
             3'b001:begin Y7=1'b1; Y6=1'b1; Y5=1'b1; Y4=1'b1; Y3=1'b1; Y2=1'b1; Y1=1'b0; Y0=1'b1;end
             3'b010:begin Y7=1'b1; Y6=1'b1; Y5=1'b1; Y4=1'b1; Y3=1'b1; Y2=1'b0; Y1=1'b1; Y0=1'b1;end
             3'b011:begin Y7=1'b1; Y6=1'b1; Y5=1'b1; Y4=1'b1; Y3=1'b0; Y2=1'b1; Y1=1'b1; Y0=1'b1;end
             3'b100:begin Y7=1'b1; Y6=1'b1; Y5=1'b1; Y4=1'b0; Y3=1'b1; Y2=1'b1; Y1=1'b1; Y0=1'b1;end
             3'b101:begin Y7=1'b1; Y6=1'b1; Y5=1'b0; Y4=1'b1; Y3=1'b1; Y2=1'b1; Y1=1'b1; Y0=1'b1;end
             3'b110:begin Y7=1'b1; Y6=1'b0; Y5=1'b1; Y4=1'b1; Y3=1'b1; Y2=1'b1; Y1=1'b1; Y0=1'b1;end
             3'b111:begin Y7=1'b0; Y6=1'b1; Y5=1'b1; Y4=1'b1; Y3=1'b1; Y2=1'b1; Y1=1'b1; Y0=1'b1;end
            default: begin Y7=1'b1; Y6=1'b1; Y5=1'b1; Y4=1'b1; Y3=1'b1; Y2=1'b1; Y1=1'b1; Y0=1'b1;end
            endcase
        else begin Y7=1'b1; Y6=1'b1; Y5=1'b1; Y4=1'b1; Y3=1'b1; Y2=1'b1; Y1=1'b1; Y0=1'b1;end
     end
endmodule   