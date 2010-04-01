(ns cascalog.core
  (:use [cascalog vars util])
  (:require [cascalog [workflow :as w] [predicate :as p]])
  (:import [cascading.tap Tap])
  (:import [cascading.tuple Fields]))

;; TODO:
;; 
;; 4. Enforce !! rules -> only allowed in generators or output of operations, ungrounds whatever it's in

(defn build-rule [out-vars predicates]
  predicates
  )


;; probably not going to work to refer to build-predicate since user hasn't necessarily required it
(defn- make-predicate-builder [pred]
  (let [[op-sym & vars] pred
        str-vars (vars2str vars)]
  (cons 'cascalog.predicate/build-predicate (cons op-sym (cons (try-resolve op-sym) str-vars)))))

(defmacro <-
  "Constructs a rule from a list of predicates"
  [outvars & predicates]
  (let [predicate-builders (vec (map make-predicate-builder predicates))
        outvars-str (vars2str outvars)]
        `(cascalog.core/build-rule ~outvars-str ~predicate-builders)))

