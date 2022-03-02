(ns aoc-2021.day-02
  (:require [aoc-2021.common :refer [parse-input]]
            [clojure.string :as s]))

(def data (parse-input "day_02"))

(defn parse-instructions
  [data]
  (->> data
       (map #(s/split % #" "))
       (map (fn [[direction val]]
              [(keyword direction) (Integer/parseInt val)]))))

;; Part 1

(defn handle-instruction-1
  [[x y] [kw val]]
  (case kw
    :forward [(+ x val) y]
    :up [x (- y val)]
    :down [x (+ y val)]))

(defn part-1
  [data]
  (->> (parse-instructions data)
       (reduce handle-instruction-1 [0 0])
       (apply *)))

(part-1 data)

;; Part 2

(defn handle-instruction-2
  [[aim x y] [kw val]]
  (case kw
    :forward [aim (+ x val) (+ y (* aim val))]
    :up [(- aim val) x y]
    :down [(+ aim val) x y]))

(defn part-2
  [data]
  (->> (parse-instructions data)
       (reduce handle-instruction-2 [0 0 0])
       rest
       (apply *)))

(part-2 data)
