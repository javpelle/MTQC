/**
 * This code is part of MTQC.
 * 
 * Copyright (c) 2020 Javier Pellejero, Luis Aguirre.
 * 
 * This code is licensed under the MIT License. You may obtain a copy 
 * of this license in the LICENSE file in the root directory of this source tree 
 * or at https://github.com/javpelle/MTQC/blob/master/LICENSE.
 */

package model.mutantoperator.qsharp;

public class OneZero extends QSharpConstant {
	@Override
	public String getName() {
		return "One_Zero";
	}

	@Override
	public String getDescription() {
		return "Replace One constant with Zero constant.";
	}

	@Override
	public String getSearchOperator() {
		return "One";
	}

	@Override
	public String getMutantOperator() {
		return "Zero";
	}

}
