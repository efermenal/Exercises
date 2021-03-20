import java.lang.StringBuilder
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.abs


fun angryProfessor(k: Int, a: Array<Int>): String {
    var arrivedOnTime = 0

    for (i in a.indices){
        if (a[i] <= 0 ){
            arrivedOnTime++
        }
    }

    if(arrivedOnTime >= k){
        return  "NO"
    }
    return  "YES"
}

fun migratoryBirds(arr: Array<Int>): Int {
    val map = HashMap<Int, Int>()
    var lowestId = 0
    var quantity = Int.MIN_VALUE
    for(i in arr.indices){
        if(!map.containsKey(arr[i])){
            map.put(arr[i], 1)
        }else{
            map[arr[i]] = map.get(arr[i])?.plus(1)!!
        }
    }

    for ((k, v) in map){
        if (v > quantity){
            lowestId = k
            quantity = v
        }else if (v == quantity && k < lowestId){
            lowestId = k
        }
    }

    return lowestId
}


fun twoStrings(s1: String, s2: String): String {

    val map : HashMap<Char,Int> = HashMap<Char, Int>()
    for(i in 0..(s1.length-1)){
        map.put(s1.get(i), 1)
    }

    for(i in 0..(s2.length-1)){
        if(map.containsKey(s2.get(i))){
            return "YES"
        }
    }
    return "NO"
}

fun diagonalDifference(arr: Array<Array<Int>>): Int {

    var leftD = 0
    var rightD = 0

    for (i in arr.indices){
        for (j in arr.indices){
            if(i == j){
                leftD += arr[i][j]
            }
            if(j+i == (arr.size -1)){
                rightD += arr[i][j]
            }
        }
    }

    return abs(rightD - leftD)
}

fun hourglassSum(arr: Array<Array<Int>>): Int {

    var maxSumHourglass = Int.MIN_VALUE

    for (i in 0..arr.size - 3){
        for (j in 0..arr.size - 3){
            val topSum = arr[i][j] + arr[i][j+1] + arr[i][j+2]
            val midSum = arr[i+1][j+1]
            val bottomSum = arr[i+2][j] + arr[i+2][j+1] + arr[i+2][j+2]

            if ((topSum + midSum + bottomSum) > maxSumHourglass){
                maxSumHourglass = topSum + midSum + bottomSum
            }
        }
    }
    return maxSumHourglass
}

fun isBalanced(s: String): String {
    val map = HashMap<Char, Char>()
    map.put(')', '(')
    map.put('}', '{')
    map.put(']', '[')

    if(s.length <= 0){
        return "NO"
    }
    val q = Stack<Char>()
    for (i in s.indices){
        if(map.containsValue(s[i])){
            q.push(s[i])
        }else if (q.empty()) {
            return  "NO"
        }else{

            val lastEnclosure = q.pop()
            val rightClosure = map[s[i]]
            if (!lastEnclosure?.equals(rightClosure)!!){
                return  "NO"
            }

        }
    }

    if (q.size == 0){
        return "YES"
    }
    return "NO"
}

fun getLargestString(s : String, k: Int) : String{

    val wordSorted = s.toCharArray().sorted()
    var newWord = StringBuilder()
    val map = hashMapOf<Char, Int>()

    for(i in wordSorted.indices){
        if(!map.containsKey(wordSorted[i])){
            map.put(wordSorted[i], 1)
        }else{
            map[wordSorted[i]] = map.get(wordSorted[i])?.plus(1)!!
        }
    }


    var result = map.toSortedMap(compareByDescending { it })
    do {
        println("-> $map")


        for ((key, v) in result){
            if (v > k) {
                for (i in 1..k){
                    newWord.append(key)
                    val q = result?.get(key)?.minus(1)!!
                    if(q == 0){
                        map.remove(key)
                    }else{
                        result[key] = q
                        println("q $q")
                    }
                }
            }else{
                for (i in 1..v){
                    newWord.append(key)
                    val q = result?.get(key)?.minus(1)!!
                    if(q == 0){
                        map.remove(key)
                    }else{
                        result[key] = q
                    }
                }
            }
        }


   }while (map.isNotEmpty())




 return newWord.toString()

}

fun strinfff(s : String, k: Int) : String{

    val map = hashMapOf<Char, Int>()
    val wordSorted = s.toCharArray().sorted()
    var newWord = StringBuilder()

    for(i in wordSorted.indices){
        if(map.containsKey(s[i])){
            map[s[i]] = map.get(s[i])?.plus(1)!!
        }else{
            map.put(s[i], 1)
        }
    }

    val array = map.keys.toTypedArray().reversed()

    for (i in array.reversed().indices){
        if (map[array[i]] != 0){
            do {

               if ( map[array[i]]!! > k) {
                   for (j in 1..k) {
                       newWord.append(array[i])
                       map[array[i]] = map[array[i]]?.minus(1)!!
                   }

                  if (i+1 < map.size) {
                      if (map[array[i]]!! != 0 && map[array[i + 1]]!! > 0) {
                          newWord.append(array[i + 1])
                          map[array[i + 1]] = map[array[i + 1]]?.minus(1)!!
                      } else {
                          map[array[i]] = map[array[i]]?.minus(1)!!
                      }
                  }else{
                      map[array[i]] = map[array[i]]?.minus(1)!!
                  }

               }else{
                   newWord.append(array[i])
                   map[array[i]] = map[array[i]]?.minus(1)!!
               }

            }while (map[array[i]] != 0)
        }

    }


    return newWord.toString()
}

fun countBinary(s : String): Int{

    var q  = 0
    val windowLimit = 3
    for (i in 0..s.length-2){
        if (s[i] != s[i+1]){
            println(s.substring(i,i+2))
            q++
        }

        if(i + windowLimit < s.length) {
            if (s[i] == s[i + 1] && s[i + 2] == s[i + 3] && s[i] != s[i+2]) {
                println("${s.substring(i,i+4)}")
                q++
            }
        }
    }

    return q
}

fun compareStrings (a: String, b : String) : String{

    if (a.length != b.length || (a.isEmpty() && b.isEmpty())){
        return "Yes"
    }else{

        val initB = b[0]
        val arrayA = a.toCharArray()

        if (a[0] != initB) {
            val index = arrayA.toList().indexOf(initB)
            if (index > -1 && index % 2 == 0) {
                val swap = a[index]
                arrayA[index] = a[0]
                arrayA[0] = swap
            } else {
                return "No"
            }
        }
        var newA = String()
        var newB = String()
        if (arrayA[0] == initB) {
            newA = String(arrayA).substring(1, a.length - 1)
            newB = b.substring(1, b.length - 1)
        } else {
            return "No"
        }

        return compareStrings(newA, newB)
    }
}

fun twins( a: Array<String>, b : Array<String> ):Array<String>{
    /*
    val strings1: Array<String?> = arrayOfNulls<String>(10000)
    val strings2: Array<String>  = Array(10000, {"string"})
   */
    val sizeMinArray = Math.min(a.size, a.size)
    var arrayResult =Array<String>(sizeMinArray, {"No"})


    for (i in 0..sizeMinArray-1){
        arrayResult[i] = compareStrings(a[i], b[i])
        println( arrayResult[i] )

    }

    return arrayResult
}

fun main(){

    val result = twins(arrayOf("abcd", "abcd"), arrayOf("cbad", "adbc"))
    println(result.contentToString())
}