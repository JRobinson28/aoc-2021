(ns aoc-2021.day-06
  (:require [aoc-2021.common :refer [parse-input]]))

(defn- format-data
  [filename]
  (->> (parse-input filename)
       (re-seq #"\d+")
       (map parse-long)
       frequencies))

(defn- update-counts
  [data]
  (let [new-fish (get data 0 0)]
    (-> (reduce #(assoc %1 (dec %2) (get %1 %2 0)) data (range 1 9))
        (update 6 + new-fish)
        (assoc 8 new-fish))))


(defn- count-fish
  [filename num-days]
  (let [data (format-data filename)]
    (loop [i 0
           data data]
      (if (= num-days i)
        (apply + (vals data))
        (recur (inc i) (update-counts data))))))

;; Part-1
(defn part-1
  [filename]
  (count-fish filename 80))

;; Part-2
(defn part-2
  [filename]
  (count-fish filename 256))
