package com.github.bgrebennikov.devbuff.data.mappers

import android.util.Log
import com.github.bgrebennikov.devbuff.common.TAG
import com.github.bgrebennikov.devbuff.data.remote.models.explore.IdeaModel
import com.github.bgrebennikov.devbuff.data.local.explore.MappedIdeaModel
import com.github.bgrebennikov.devbuff.data.local.explore.MappedIdeaRequirements
import com.github.bgrebennikov.devbuff.data.local.explore.MappedLanguagesModel
import com.github.bgrebennikov.devbuff.data.local.explore.MappedTechnologiesModel
import com.github.bgrebennikov.devbuff.data.local.explore.ideaDetails.MappedIdeaDetailsModel
import com.github.bgrebennikov.devbuff.data.local.explore.ideaDetails.MappedIdeaFrameworks
import com.github.bgrebennikov.devbuff.data.local.explore.ideaDetails.MappedIdeaSpecialists
import com.github.bgrebennikov.devbuff.data.local.explore.ideaDetails.MappedIdeaSpecialistsLanguages
import com.github.bgrebennikov.devbuff.data.remote.models.explore.ideaDetails.IdeaDetailsModel
import com.github.bgrebennikov.devbuff.data.remote.models.explore.ideaDetails.IdeaDetailsSpecialists

class MapperIdeas{

    private fun mapToSimpleIdeaObject(ideaModel: IdeaModel) : MappedIdeaModel {
        val requirementsHashSet = hashSetOf<MappedIdeaRequirements>()
        val technologiesHashSet = hashSetOf<MappedTechnologiesModel>()
        val languagesHashSet = hashSetOf<MappedLanguagesModel>()

        ideaModel.requirements.map { req ->
            requirementsHashSet.add(
                MappedIdeaRequirements(
                    req.name
                )
            )

            req.languages.map { languages ->
                languagesHashSet.add(
                    MappedLanguagesModel(
                        languages.name
                    )
                )

                languages.technologies.map { technologies ->
                    technologiesHashSet.add(
                        MappedTechnologiesModel(
                            technologies.name
                        )
                    )
                }

            }
        }


        return MappedIdeaModel(
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

    fun mapToSimpleIdeasList(ideaModel: List<IdeaModel>): List<MappedIdeaModel> {


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

    private fun mapToLocalSpecialistsModel(specialists: IdeaDetailsSpecialists): MappedIdeaSpecialists {

        val languagesHashSet = hashSetOf<MappedIdeaSpecialistsLanguages>()
        val frameworksHashSet = hashSetOf<MappedIdeaFrameworks>()

        specialists.languages.map { lang ->

            languagesHashSet.add(
                MappedIdeaSpecialistsLanguages(
                    lang.name
                )
            )

            lang.frameworks.map { framework ->
                frameworksHashSet.add(
                    MappedIdeaFrameworks(
                        framework.name
                    )
                )
            }

        }

        return MappedIdeaSpecialists(
            id = specialists.id,
            count = specialists.count,
            name = specialists.name,
            languages = languagesHashSet.toList(),
            frameworks = frameworksHashSet.toList()
        )

    }

    fun mapToLocalIdeaDetails(model: IdeaDetailsModel): MappedIdeaDetailsModel {
        return MappedIdeaDetailsModel(
            id = model.id,
            ownerIdea = model.ownerIdea,
            name = model.name,
            status = model.status,
            description = model.description,
            body = model.body,
            specialist = model.specialist.map {
                mapToLocalSpecialistsModel(
                    it
                )
            }
        )
    }

}