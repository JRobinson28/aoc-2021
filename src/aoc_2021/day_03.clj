(ns aoc-2021.day-03
  (:require [aoc-2021.common :refer [parse-input parse-int-radix]]
            [clojure.string :as s]))

(defn get-length-range
  [filename]
  (-> filename parse-input s/split-lines first count range))

(defn value-at-index
  [data index fn val-to-keep]
  (let [frequencies (->> data
                         (map #(nth % index))
                         frequencies)]
    (if (= 1 (-> frequencies vals set count))
      val-to-keep
      (key (apply fn val frequencies)))))

(defn filter-fn
  [data index fn val-to-keep]
  (if (= 1 (count data))
    data
    (let [filter-val (value-at-index data index fn val-to-keep)]
      (filter #(= filter-val (nth % index)) data))))

;; Part 1
(defn part-1
  [filename]
  (let [length (get-length-range filename)
        data (s/split-lines (parse-input filename))
        gamma (map #(value-at-index data % max-key \1) length)
        epsilon (map #(value-at-index data % min-key  \0) length)]
    (->> (vector gamma epsilon)
         (map (comp #(parse-int-radix % 2) s/join))
         (apply *))))

(part-1 "day_03")

;;Part 2
(defn part-2
  [filename]
  (let [length (get-length-range filename)
        data (s/split-lines (parse-input filename))
        oxygen (reduce #(filter-fn %1 %2 max-key \1) data length)
        co2 (reduce #(filter-fn %1 %2 min-key \0) data length)]
    (->> (concat oxygen co2)
         (map #(parse-int-radix % 2))
         (apply *))))

(part-2 "day_03")
