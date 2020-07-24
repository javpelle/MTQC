namespace Quantum.DeutschJozsa {
    open Microsoft.Quantum.Intrinsic;
    open Microsoft.Quantum.Canon;
    open Microsoft.Quantum.Diagnostics;
    
	operation DeutschJozsa(register : Qubit[], U_f : (Qubit[] => Unit)) : String {
        
        let nQubits = Length(register);
        if (nQubits != 5) {
            return "El numero de Qubits debe ser 5 \n";
        }
        else {
            //Negación del último Qubit (salida)
            X(register[nQubits - 1]);

            //Poner cada qubit en superposición
            for(q in register) {
                H(q);
            }
            
            //Aplicamos la Uf
            U_f(register);

            //Aplicamos una puerta Hadamard a cada Qubit de entrada
            for(i in 0..nQubits - 2) {
                H(register[i]);
            }
            
            //La funcion será constante si medimos el estado |0000>. Balanceada en otro caso.
             mutable allZeros = true;
             for(i in 0..nQubits - 2) {
                if(M(register[i]) == One){
                     set allZeros = false;
                }
            }

            if (allZeros){
                return "Constante";
            } else {
                return "Balanceada";
            }
        }
        
	}
}
