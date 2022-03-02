(ns aoc-2021.day-03
  (:require [aoc-2021.common :refer [parse-input]]
            [clojure.string :as s]))

(def data (parse-input "day_03"))

(def length (range (count (first data))))

(defn value-at-index
  [data index fn val-to-keep]
  (let [frequencies (->> data
                         (map #(nth % index))
                         frequencies)]
    (if (= 1 (-> frequencies vals set count))
      val-to-keep
      (key (apply fn val frequencies)))))

(defn power-consumption
  [data]
  (let [gamma (map #(value-at-index data % max-key \1) length)
        epsilon (map #(value-at-index data % min-key  \0) length)]
    (->> (vector gamma epsilon)
         (map (comp #(Integer/parseInt % 2) s/join))
         (apply *))))

;; Part 1
(power-consumption data)

(defn filter-fn
  [data index fn val-to-keep]
  (if (= 1 (count data))
    data
    (let [filter-val (value-at-index data index fn val-to-keep)]
      (filter #(= filter-val (nth % index)) data))))

(defn life-support-rating
  [data]
  (let [length (range (count (first data)))
        oxygen (reduce #(filter-fn %1 %2 max-key \1) data length)
        co2 (reduce #(filter-fn %1 %2 min-key \0) data length)]
    (->> (concat oxygen co2)
         (map #(Integer/parseInt % 2))
         (apply *))))

;; Part 2
(life-support-rating data)
