# This code is part of MTQC.
#
# Copyright (c) 2020 Javier Pellejero, Luis Aguirre.
#
# This code is licensed under the MIT License. You may
# obtain a copy of this license in the LICENSE file in the root directory
# of this source tree or at https://github.com/javpelle/MTQC/blob/master/LICENSE.

from tools.func_timeout import func_timeout, FunctionTimedOut
import json

def run_qsharp_shots (function, timeout, shots, d, QStateTest = False):
    aux_d = {}
    for i in range(shots):
        try:
            if QStateTest:
                doitReturnValue = func_timeout(timeout, function)
                filetemp = open("temp.txt", "r", encoding='utf-8') 
                aux_list = ''
                #We discard first info line from dump machine
                aux = list(filetemp)[1:]
                for x in aux:
                    # We dont want the first chars from dump machine from each line. Ket chars cause problems regarding UTF8 encoding.
                    # We just need amplitud from each state.
                    aux_list = aux_list + x[x.find('[') : x.find(']') + 1]
                aux_list = aux_list[1:-1]
                aux_list = aux_list.replace(",",".")
                aux_list = aux_list.split("][")
                aux_list = list(map(float, aux_list))
                for i in range(len(aux_list)):
                    aux_d[i] = int(round(aux_list[i], 2) * 100)
                filetemp.close()
            else:
                doitReturnValue = func_timeout(timeout, function)
                if doitReturnValue in aux_d:
                    aux_d[doitReturnValue] = aux_d[doitReturnValue] + 1
                else:
                    aux_d[doitReturnValue] = 1
        except FunctionTimedOut:
            if "TimeLimit" in aux:
                aux_d["-1"] = aux_d["-1"] + 1
            else:
                aux_d["-1"] = 1
        except Exception as e:
            if str(e) in aux:
                aux_d["-2"] = aux_d["-2"] + 1
            else:
                aux_d["-2"] = 1
    d[len(d)] = aux_d
			
def run_qiskit_shots(function, timeout, shots, d):
	try:
		doitReturnValue = func_timeout(timeout * shots, function)
		if isinstance(doitReturnValue, dict):
			d[len(d)] = doitReturnValue
		else:
			# list -> statevector
			aux = {}
			for i in range(len(doitReturnValue)):
				aux[i] = int(round(doitReturnValue[i], 2) * 100)
			d[len(d)] = aux
	except FunctionTimedOut:
		d[len(d)] = {"-1":-1}
	except Exception as e:
		d[len(d)] = {"-2":-2}
	print("Test number ", len(d), " completed.")
	
def save_data(d):
	with open('data.json', 'w') as fp:
		json.dump(d, fp, indent=4)
