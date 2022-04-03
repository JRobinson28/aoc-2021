(ns aoc-2021.day-01
  (:require [aoc-2021.common :refer :all]
            [clojure.string :as s]))

(defn parse-data
  [filename]
  (->> (parse-input filename)
       (s/split-lines)
       (map parse-long)))

(defn count-increases
  [data]
  (->> data
       (map > (rest data))
       (filter identity)
       count))

;; Part 1
(defn part-1
  [filename]
  (count-increases (parse-data filename)))

(part-1 "day_01")

;; Part 2
(defn part-2
  [filename]
  (->> (parse-data filename)
       (partition 3 1)
       (map #(apply + %))
       count-increases))

(part-2 "day_01")
