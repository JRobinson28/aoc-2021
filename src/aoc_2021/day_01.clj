(ns aoc-2021.day-01
  (:require [aoc-2021.common :refer [parse-input]]))

(def data (->> (parse-input "day_01")
               (map #(Integer/parseInt %))))

(defn count-increases
  [data]
  (->> data
       (map > (rest data))
       (filter identity)
       count))

;; Part 1
(count-increases data)

(defn count-window-increases
  [data]
  (->> data
       (partition 3 1)
       (map #(apply + %))
       count-increases))

;; Part 2
(count-window-increases data)
