package com.github.bgrebennikov.devbuff.data.mappers

import android.text.format.DateUtils
import android.util.Log
import com.github.bgrebennikov.devbuff.common.TAG
import com.github.bgrebennikov.devbuff.data.remote.models.explore.IdeaModel
import com.github.bgrebennikov.devbuff.data.local.SimpleIdeaModel
import com.github.bgrebennikov.devbuff.data.local.SimpleIdeaRequirements
import com.github.bgrebennikov.devbuff.data.local.SimpleLanguagesModel
import com.github.bgrebennikov.devbuff.data.local.SimpleTechnologiesModel
import java.text.SimpleDateFormat
import java.util.*

class MapperIdeas{

    private fun mapToSimpleIdeaObject(ideaModel: IdeaModel) : SimpleIdeaModel {
        val requirementsHashSet = hashSetOf<SimpleIdeaRequirements>()
        val technologiesHashSet = hashSetOf<SimpleTechnologiesModel>()
        val languagesHashSet = hashSetOf<SimpleLanguagesModel>()

        ideaModel.requirements.map { req ->
            requirementsHashSet.add(
                SimpleIdeaRequirements(
                    req.name
                )
            )

            req.languages.map { languages ->
                languagesHashSet.add(
                    SimpleLanguagesModel(
                        languages.name
                    )
                )

                languages.technologies.map { technologies ->
                    technologiesHashSet.add(
                        SimpleTechnologiesModel(
                            technologies.name
                        )
                    )
                }

            }
        }


        return SimpleIdeaModel(
            ideaModel.id,
            ideaModel.ownerIdea,
            ideaModel.name,
            ideaModel.description,
            requirementsHashSet.toList(),
            technologiesHashSet.toList(),
            languagesHashSet.toList(),
            ideaModel.updateDate,
            ideaModel.publishDate
        )

    }

    fun mapToSimpleIdeasList(ideaModel: List<IdeaModel>): List<SimpleIdeaModel> {


        val requirementsHashSet = hashSetOf<String>()
        val technologiesHashSet = hashSetOf<String>()
        val languagesHashSet = hashSetOf<String>()

        Log.i(TAG, "mapToSimpleIdeasList: $ideaModel")

        ideaModel.map { idea ->
            idea.requirements.map { req ->
                requirementsHashSet.add(req.name)

                req.languages.map { languages ->
                    languagesHashSet.add(languages.name)

                    languages.technologies.map { technologies ->
                        technologiesHashSet.add(technologies.name)
                    }

                }
            }
        }

        return ideaModel.map { mapToSimpleIdeaObject(it) }


    }

}