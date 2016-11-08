package com.demo.bookkeeper;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.bookkeeper.client.BookKeeper;
import org.apache.bookkeeper.client.LedgerEntry;
import org.apache.bookkeeper.client.LedgerHandle;

public class Demo {

	public static void main(String[] args) {
		
		try {
			List<byte[]> entries = new ArrayList<byte[]>();
			byte[] pass = "pass".getBytes(Charset.forName("UTF-8"));
			
			BookKeeper bkc = new BookKeeper("localhost:2181");
			LedgerHandle lh = bkc.createLedger(BookKeeper.DigestType.CRC32, pass);
			long ledgerId = lh.getId();
			ByteBuffer entry = ByteBuffer.allocate(4);

			for(int i = 0; i < 10; i++){
				entry.putInt(i);
				entry.position(0);
				lh.addEntry(entry.array());
				entries.add(entry.array());
			}
			lh.close();
			lh = bkc.openLedger(ledgerId, BookKeeper.DigestType.CRC32, pass);		
						
			Enumeration<LedgerEntry> ls = lh.readEntries(0, 9);
			int i = 0;
			while(ls.hasMoreElements()){
				ByteBuffer origbb = ByteBuffer.wrap(
							entries.get(i++));
				Integer origEntry = origbb.getInt();
				ByteBuffer result = ByteBuffer.wrap(
							ls.nextElement().getEntry());

				Integer retrEntry = result.getInt();
				System.out.println(origEntry + " : " + retrEntry);
			}
			lh.close();
			bkc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
