from qiskit import *

def deutschjozsa (qc, qr, cr, uf):
    if len(qr) != 5:
        print("El número de qubits debe ser 5.")
        return
    
    # Negación  del último qubit
    qc.x(qr[-1])
    
    qc.barrier()
    
    # Aplicación de una puerta de Hadamard a cada qubit
    for r in qr:
        qc.h(r)
        
    qc.barrier()
        
    # Aplicamos U_f
    uf(qc, qr)
    
    qc.barrier()
    
    # Aplicación de una puerta de Hadamard a cada qubit de entrada
    for r in qr[:-1]:
        qc.h(r)
        
    qc.barrier()
    
    qc.measure(qr[:-1], cr)
