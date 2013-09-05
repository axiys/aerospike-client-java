/*
 * Aerospike Client - Java Library
 *
 * Copyright 2013 by Aerospike, Inc. All rights reserved.
 *
 * Availability of this source code to partners and customers includes
 * redistribution rights covered by individual contract. Please check your
 * contract for exact rights and responsibilities.
 */
package com.aerospike.client.async;

import com.aerospike.client.AerospikeException;
import com.aerospike.client.Key;
import com.aerospike.client.listener.ExistsSequenceListener;

public final class AsyncBatchExistsSequence extends AsyncMultiCommand {
	private final ExistsSequenceListener listener;
	
	public AsyncBatchExistsSequence(AsyncMultiExecutor parent, AsyncCluster cluster, AsyncNode node, ExistsSequenceListener listener) {
		super(parent, cluster, node, false);
		this.listener = listener;
	}
		
	@Override
	protected void parseRow(Key key) throws AerospikeException {		
		if (opCount > 0) {
			throw new AerospikeException.Parse("Received bins that were not requested!");
		}			
		listener.onExists(key, resultCode == 0);
	}
}
