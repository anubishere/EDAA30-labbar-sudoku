package map;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private static double loadFactor = 0.75;
	Entry<K,V>[] entryMap;
	int size;
	int capacity;
	private boolean rehashing;
	
	public SimpleHashMap() {
		capacity=16;
		rehashing=false;
		size=0;
		entryMap =(Entry<K,V>[]) new Entry[capacity];
	}
	
	public SimpleHashMap(int capacity) {
		this.capacity=capacity;
		entryMap =(Entry<K,V>[]) new Entry[capacity];
	}
	
	public String show() {
		StringBuilder showString = new StringBuilder();
		for(Entry<K,V> a: entryMap) {
			showString.append("\n");
			while(a!=null) {
				showString.append(a.getKey()+ "="+a.getValue());	
				a=a.next;
			}
		}
		
		return showString.toString();
	}
	/**public String show() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < entryMap.length; i++) {
			sb.append(i + "\t");
			if (entryMap[i] != null) {
				Entry<K, V> e = entryMap[i];
				while (e != null) {
					sb.append(e.toString() + ", ");
					e = e.next;
				}
			} else {
				sb.append("empty");
			}
			sb.append("\n");
		}
		return sb.toString();
	} */

	
	private int index(K key) {
		int index= key.hashCode() % capacity;
		index=Math.abs(index);
		return index;
	}
	
	private  Entry<K,V> find(int index, K key){
		Entry<K,V> e = entryMap[index];		
		while(e!=null) {
			if(e.getKey().equals(key)) {
				return e;
			}
			e=e.next;
		}		
		return null;
	}
	
	@Override
	public V get(Object arg0) {
		K key = (K) arg0;	
		Entry<K,V> e=find(index(key), key);	
		if(e!=null) {
			return e.v;
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size==0;
	}

	@Override
	public V put(K key, V value) {
		int index=index(key);
		Entry<K,V> find = find(index, key);
		if(find!=null) {
			return find.setValue(value);
		} else {
			Entry<K,V> e = new Entry(key, value);
			if (entryMap[index]!=null) {
				e.next=entryMap[index];
				entryMap[index] = e;
			}	else {
				entryMap[index]=e;
			}
			if(!rehashing) {
				size++;
			} 
			if((double)(size/capacity)>0.75) {
				rehash();
			}
		}		
		return null;
	}
	
	private void rehash() {
		rehashing=true;
		capacity*=2;
		Entry<K,V>[] oldMap=entryMap;
		entryMap = (Entry<K,V>[]) new Entry[capacity];
		
		for(Entry<K,V> a : oldMap) {
			Entry<K,V> current = a;
			while(current!=null) {
				put(current.k, current.v);
				current=current.next;
			}
		}
		
		rehashing=false;
	}

	@Override
	public V remove(Object arg0) {
		if(!isEmpty()) {
			K key = (K) arg0;
			int index = index(key);
			Entry<K,V> toRemove = find(index, key);
			if(toRemove!=null) {
				
				Entry<K,V> e = entryMap[index];
			//om det är först i listan tar vi bort det
			if(e.getKey().equals(key)) {
				entryMap[index]=e.next;
				size--;
				return e.getValue();
			}
			
			//om det inte är först går vi igenom listan tills vi hittar det vi vill ta bort
			//därefter gör vi en temporär att returnera, samt hoppar över en nod  i listan
			while(e.next!=null) {
				if(e.next.getKey().equals(key)) {
					Entry<K,V> temp = e.next;
					e.next=e.next.next;
					size--;
					return temp.getValue();
				}
				e=e.next;
			}
		}
		}
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	
	static class Entry<K,V> implements Map.Entry<K,V>{

		K k;
		V v;	
		Entry<K,V> next;
		
		private Entry(K key, V value){
			k=key;
			v=value;
		}
		
		@Override
		public K getKey() {
			return k;
		}

		@Override
		public V getValue() {
			return v;
		}

		@Override
		public V setValue(V value) {
			V temp = v;
			v=value;
			return temp;
		}
		
		@Override
		public String toString() {
			return k + "="+ v;
		}
		
	}
}
