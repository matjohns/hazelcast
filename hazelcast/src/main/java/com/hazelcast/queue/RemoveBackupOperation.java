/*
 * Copyright (c) 2008-2012, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.queue;

import com.hazelcast.spi.BackupOperation;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @ali 12/11/12
 */
public class RemoveBackupOperation extends QueueOperation implements BackupOperation {

    private long itemId;

    public RemoveBackupOperation() {
    }

    public RemoveBackupOperation(String name, long itemId) {
        super(name);
        this.itemId = itemId;
    }

    public void run() throws Exception {
        getContainer().removeBackup(itemId);
        response = true;
    }

    public void writeInternal(DataOutput out) throws IOException {
        super.writeInternal(out);
        out.writeLong(itemId);
    }

    public void readInternal(DataInput in) throws IOException {
        super.readInternal(in);
        itemId = in.readLong();
    }
}
