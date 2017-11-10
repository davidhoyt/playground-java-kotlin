package playground;

//class Node {
//    Node next;  // Next node in the linked list
//    Node value; // Any other node in the linked list
//}
//
//A(C) -> B(B) -> C() -> D(B)
//
//W(Y) -> X(X) -> Y() -> Z(X)
//
//// O(N)
//def copy(head: Node): Node = {
//  val indexes = collection.mutable.HashMap[Node, Int]
//  var current: Node = head
//  var index = 0
//
//  while (current != null) {
//    indexes += (current -> index)
//    index += 1
//    current = current.next;
//  }
//
//  val mapNodeToIndex = collection.mutable.HashMap[Int, Int]
//
//  while (current != null) {
//    mapNodeToIndex += indexes(current) -> indexes(current.value)
//    current = current.next
//  }
//
//  val nodeCopies: HashMap[Int, Node] = indexes.map { case (node, idx) =>
//    idx -> new Node()
//  }
//
//  val withValueAssigned: List[Node] = nodeCopies.map { case (idx, node) =>
//    node.next = nodeCopies.getOrElse(idx + 1, null)
//    node.value = mapNodeToIndex.getOrElse(idx, null)
//    node
//  }
//
//  withValueAssigned.head
//}
