# This code is part of MTQC.
#
# Copyright (c) 2020 Javier Pellejero, Luis Aguirre.
#
# This code is licensed under the MIT License. You may
# obtain a copy of this license in the LICENSE file in the root directory
# of this source tree or at https://github.com/javpelle/MTQC/blob/master/LICENSE.

from tools.func_timeout import func_timeout, FunctionTimedOut
import json

def run_shots (function, timeout, shots, keyStart = '_mtqc_s', keyEnd = '_mtqc_e', QStateTest = False):
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
				print(keyStart, aux_list)			
				print(keyEnd)
				filetemp.close()
			else:
				doitReturnValue = func_timeout(timeout, function)
				print(keyStart, doitReturnValue)
				print(keyEnd)
		except FunctionTimedOut:
			print (keyStart, "TimeLimit")
			print(keyEnd)
		except Exception as e:
			print (keyStart, e)
			print(keyEnd)
			
def run_qiskit_shots(function, timeout, shots, d):
	try:
		doitReturnValue = func_timeout(timeout * shots, function)
		if isinstance(doitReturnValue, dict):
			d[len(d)] = doitReturnValue
		else:
			# list -> statevector
			aux = {}
			for i in range(len(doitReturnValue)):
				aux[i] = round(doitReturnValue[i], 2)
			d[len(d)] = aux
	except FunctionTimedOut:
		d[len(d)] = "TimeLimit"
	except Exception as e:
		d[len(d)] = e
			
def save_data(d):
	with open('data.json', 'w') as fp:
		json.dump(d, fp, indent=4)
